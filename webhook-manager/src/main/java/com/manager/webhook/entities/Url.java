package com.manager.webhook.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "webhooks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String url;

    private List<String> events; //new book - new book List

}
