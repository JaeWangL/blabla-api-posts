package com.blabla.posts.api.infrastructure.mappers;

import com.blabla.posts.api.domain.seedwork.Entity;
import com.blabla.posts.api.infrastructure.entities.BaseEntity;

interface EntityMapper<E extends BaseEntity, D extends Entity<?>> {
    E toEntity(D domainEntity);

    D fromEntity(E entity);
}
