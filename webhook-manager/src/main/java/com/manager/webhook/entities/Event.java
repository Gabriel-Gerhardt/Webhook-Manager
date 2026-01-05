package com.manager.webhook.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Table(name = "events")
@Getter
public class Event {

    @Id
    @Column(unique = true)
    UUID id;

    @Column(unique = true,nullable = false)
    String name;

    @Column(nullable = false)
    String url;

}
