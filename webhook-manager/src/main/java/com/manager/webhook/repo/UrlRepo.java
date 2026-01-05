package com.manager.webhook.repo;

import com.manager.webhook.entities.UrlRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRequestRepo extends JpaRepository<UrlRequest, UUID> {
    Optional<UrlRequest> findByObserverUrl(String observerUrl);
}
