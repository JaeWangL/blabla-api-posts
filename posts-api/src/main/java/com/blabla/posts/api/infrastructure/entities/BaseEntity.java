package com.blabla.posts.api.infrastructure.entities;

import javax.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Getter
    protected String id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Getter
    protected Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @Getter
    protected Date updatedAt;
}
