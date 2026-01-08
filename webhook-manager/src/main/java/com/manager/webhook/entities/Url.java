package com.manager.webhook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "webhooks")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String url;

    private List<String> events;
    public Url(String url, List<String> events) {
        this.url = url;
        this.events = events;
    }
}
