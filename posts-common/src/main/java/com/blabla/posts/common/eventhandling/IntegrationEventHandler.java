package com.blabla.posts.common.eventhandling;

public interface IntegrationEventHandler<T> {
    void handle(T event);
}
