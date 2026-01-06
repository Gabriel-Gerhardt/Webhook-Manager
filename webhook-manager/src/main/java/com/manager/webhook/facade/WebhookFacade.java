package com.manager.webhook.facade;

import com.manager.webhook.model.EventModel;
import com.manager.webhook.model.UrlModel;
import com.manager.webhook.service.EventService;
import com.manager.webhook.service.UrlService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebhookFacade {
    private final UrlService urlService;
    private final EventService eventService;
    public WebhookFacade(UrlService urlService, EventService eventService){
        this.urlService = urlService;
        this.eventService = eventService;
    }
    public void saveEvent(EventModel event){
        eventService.save(event);
    }
    public void saveUrl(UrlModel url){
        urlService.save(url);
    }
    public void payloadHandler(String payload, String event){
        EventModel eventModel = eventService.findByEvent(event);
        List<UrlModel> urls = urlService.findAll();

        urls.stream()
                .flatMap(url -> url.events().stream()
                        .filter(e -> e.equals(eventModel.name()))
                        .map(e -> url))
                .forEach(url -> sendPayload(payload, url.url()));
    }
    public void sendPayload(String payload,String url){

    }
}
