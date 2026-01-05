package com.manager.webhook.mapper;

import com.manager.webhook.entities.Event;
import com.manager.webhook.model.EventModel;

public class EventMapper {
    public static Event toEntity(EventModel model){
        return new Event(model.id(),model.name(),model.url());
    }
}
