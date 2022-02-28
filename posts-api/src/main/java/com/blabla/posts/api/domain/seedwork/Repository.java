package com.blabla.posts.api.domain.seedwork;

import org.springframework.lang.NonNull;

public interface Repository<T extends AggregateRoot> {
    // NOTE: Work like 'UnitOfWork'
    T save(@NonNull T aggregateRoot);
}
