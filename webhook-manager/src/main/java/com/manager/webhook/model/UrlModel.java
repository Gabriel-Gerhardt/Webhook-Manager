package com.manager.webhook.model;

import java.util.List;
import java.util.UUID;

public record UrlModel(UUID id, String url, List<String> events) {
}
