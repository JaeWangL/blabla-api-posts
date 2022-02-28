package com.blabla.posts.api.domain.seedwork;

import org.springframework.lang.NonNull;
import java.util.List;
import java.util.Objects;

public abstract class Identifier extends ValueObject {
    private final String id;

    protected Identifier(@NonNull String id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }

    @NonNull
    public String getValue() {
        return id;
    }

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(id);
    }
}
