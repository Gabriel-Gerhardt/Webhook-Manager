package com.manager.webhook.repo;

import com.manager.webhook.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepo extends JpaRepository<Url, UUID> {
}
