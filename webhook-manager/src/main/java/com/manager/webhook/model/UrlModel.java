package com.manager.webhook.model;

import java.util.List;

public record UrlModel(String url, List<String> events) {
}
