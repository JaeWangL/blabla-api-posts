package com.blabla.posts.common.eventhandling;

import com.github.ksuid.Ksuid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class IntegrationEvent {
    private final String id;
    private final LocalDateTime creationDate;

    public IntegrationEvent() {
        this(Ksuid.newKsuid().toString(), LocalDateTime.now());
    }
}
