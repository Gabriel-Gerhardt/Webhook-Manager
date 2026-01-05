package com.manager.webhook.rest;

import com.manager.webhook.entities.Url;
import com.manager.webhook.model.UrlModel;
import com.manager.webhook.service.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/url")
public class UrlController {
    private final UrlService urlService;
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/postUrl")
    public void saveUrl(@RequestBody UrlModel urlModel){
        urlService.save(urlModel);
    }
}
