package com.manager.webhook.model;

import java.util.List;
import java.util.UUID;

public record UrlRequestModel(UUID id, List<String> incomingUrl, String observerUrl) {
}
