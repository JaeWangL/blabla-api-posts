package com.blabla.posts.api.domain.events;

import com.blabla.posts.api.domain.aggregatesmodel.post.Post;
import com.blabla.posts.api.domain.seedwork.DomainEvent;

public record PostCreatedDomainEvent(Post post) implements DomainEvent {
}
