package com.blabla.posts.api.application.infrastructure.requestmanager;

/**
 * Used for handling command idempotency.
 */
public interface RequestManager {
    boolean exist(String id);

    void createRequestForCommand(String id, String commandName);
}
