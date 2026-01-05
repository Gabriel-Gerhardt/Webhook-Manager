package com.manager.webhook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "events")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(unique = true,nullable = false)
    String name;

    @Column(nullable = false)
    String url;

}
