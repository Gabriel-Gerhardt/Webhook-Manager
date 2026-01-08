package com.manager.webhook.rest;

import com.manager.webhook.facade.WebhookFacade;
import com.manager.webhook.model.EventModel;
import com.manager.webhook.model.UrlModel;
import com.manager.webhook.model.WebhookPayload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final WebhookFacade webhookFacade;
    public WebhookController(WebhookFacade webhookFacade){
        this.webhookFacade = webhookFacade;
    }

    @PostMapping("/url")
    public void saveUrl(@RequestBody UrlModel urlModel){
        webhookFacade.saveUrl(urlModel);
    }
    @PostMapping("/event")
    public void saveEvent(@RequestBody EventModel eventModel){
        webhookFacade.saveEvent(eventModel);
    }
    @PostMapping("/payload")
    public void postPayloadHandler(@RequestBody WebhookPayload webhookPayload){
        webhookFacade.dispatchEventPayload(webhookPayload.payload().toString(), webhookPayload.event());
    }
}
