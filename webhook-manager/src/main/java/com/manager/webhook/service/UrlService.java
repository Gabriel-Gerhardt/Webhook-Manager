package com.manager.webhook.service;

import com.manager.webhook.entities.Event;
import com.manager.webhook.entities.Url;
import com.manager.webhook.exception.NoValidEventFound;
import com.manager.webhook.mapper.UrlMapper;
import com.manager.webhook.model.UrlModel;
import com.manager.webhook.repo.EventRepo;
import com.manager.webhook.repo.UrlRepo;

import java.util.List;

public class UrlService {
    private final UrlRepo urlRepo;
    private final EventRepo eventRepo;
    public UrlService(UrlRepo urlRepo, EventRepo eventRepo){
        this.urlRepo = urlRepo;
        this.eventRepo = eventRepo;
    }

    public void save(UrlModel urlModel){
        List<String> filteredEvents = validEvents(urlModel);
        Url urlRequest = UrlMapper.toEntity(urlModel);
        urlRequest.setEvents(filteredEvents);
        urlRepo.save(urlRequest);

    }

    public List<String> validEvents(UrlModel urlModel){
        List<String> allEventNames = eventRepo.findAll().stream()
                .map(Event::getName)
                .toList();

        List<String> filtered = urlModel.events().stream()
                .filter(allEventNames::contains)
                .toList();

        if(filtered.isEmpty()){
            throw new NoValidEventFound("No valid event to " + urlModel.url());
        }
        return filtered;
    }
}
