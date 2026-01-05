package com.manager.webhook.mapper;

import com.manager.webhook.entities.UrlRequest;
import com.manager.webhook.model.UrlModel;

import java.util.function.Function;

public class UrlEntityToUrlModel implements Function<UrlRequest, UrlModel> {

    @Override
    public UrlModel apply(UrlRequest urlRequest) {
        return null;
    }
}
