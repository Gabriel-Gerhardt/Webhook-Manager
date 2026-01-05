package com.manager.webhook.model;


import java.util.UUID;

public record EventModel(UUID id, String name, String url) {
}
