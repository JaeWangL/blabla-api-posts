package com.blabla.posts.api.infrastructure.idempotency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRequestRepository extends JpaRepository<ClientRequest, String> {
}
