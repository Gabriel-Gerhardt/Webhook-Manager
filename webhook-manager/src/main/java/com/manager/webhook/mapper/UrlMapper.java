package com.manager.webhook.mapper;

import com.manager.webhook.entities.Url;
import com.manager.webhook.model.UrlModel;

public class UrlMapper{

    public static UrlModel toModel(Url urlRequest) {
        return new UrlModel(urlRequest.getId(),urlRequest.getUrl(), urlRequest.getEvents());
    }
    public static Url toEntity(UrlModel urlModel){
        return new Url(urlModel.id(),urlModel.url(), urlModel.events());

    }
}
