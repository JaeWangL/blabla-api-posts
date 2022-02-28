package com.blabla.posts.common.eventhandling;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class IntegrationEvent {
    private final UUID id;
    private final LocalDateTime creationDate;

    public IntegrationEvent() {
        this(UUID.randomUUID(), LocalDateTime.now());
    }
}
