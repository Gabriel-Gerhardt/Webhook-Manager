package com.project.library.webhook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final UrlRepo urlRepo;

    public WebhookController(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }


    @GetMapping("/register/{incomingUrl}/{requestUrl}")
    public void registerUrl(@RequestParam String incomingUrl,@RequestParam String requestUrl) throws SQLException {
        urlRepo.registerUrl(incomingUrl,requestUrl);
    }

    @GetMapping()
    public String event(Object o){
        return o.toString();
    }

}
