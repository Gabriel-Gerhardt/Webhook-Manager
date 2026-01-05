package com.manager.webhook.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "webhook")
@Getter
@Setter
public class UrlRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String url;

    private List<String> events; //new book - new book List
}
