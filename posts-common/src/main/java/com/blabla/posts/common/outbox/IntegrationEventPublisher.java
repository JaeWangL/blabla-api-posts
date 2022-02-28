package com.blabla.posts.common.outbox;

public interface IntegrationEventPublisher {
    void publish(IntegrationEventLogEntry eventLogEntry);
}

