package com.manager.webhook.service;

import com.manager.webhook.contract.UrlServiceContract;
import com.manager.webhook.entities.Event;
import com.manager.webhook.exception.EventNotFoundException;
import com.manager.webhook.mapper.EventMapper;
import com.manager.webhook.model.EventModel;
import com.manager.webhook.repo.EventRepo;

import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<EventModel> findAll(){
        return eventRepo.findAll()
                .stream()
                .map(EventMapper::toModel)
                .toList();
    }
    public EventModel findByEvent(String event){
        Event eventEntity = eventRepo.findByEvent(event);
        if(eventEntity==null){
            throw new EventNotFoundException("Event " + event + " does not exists");
        }
        return EventMapper.toModel(eventEntity);
    }
}
