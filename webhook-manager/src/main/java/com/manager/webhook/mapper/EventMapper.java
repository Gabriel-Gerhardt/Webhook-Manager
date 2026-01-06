package com.manager.webhook.mapper;

import com.manager.webhook.entities.Event;
import com.manager.webhook.model.EventModel;

public class EventMapper {
    public static Event toEntity(EventModel model){
        return new Event(model.id(),model.name(),model.url());
    }
    public static EventModel toModel(Event event){
        return new EventModel(event.getId(),event.getName(),event.getUrl());
    }
}
