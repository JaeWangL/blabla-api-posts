package com.blabla.posts.common.outbox;

public enum EventState {
    NotPublished,
    InProgress,
    Published,
    PublishedFailed
}
