package com.manager.webhook.service;

import com.manager.webhook.contract.UrlServiceContract;
import com.manager.webhook.entities.Event;
import com.manager.webhook.mapper.EventMapper;
import com.manager.webhook.model.EventModel;
import com.manager.webhook.repo.EventRepo;

import org.springframework.stereotype.Service;

@Service
public class EventService implements UrlServiceContract {
    private final EventRepo eventRepo;
    public EventService(EventRepo eventRepo){
        this.eventRepo = eventRepo;
    }
    public void save(EventModel eventModel){
        Event event = EventMapper.toEntity(eventModel);
        eventRepo.save(event);
    }

}
