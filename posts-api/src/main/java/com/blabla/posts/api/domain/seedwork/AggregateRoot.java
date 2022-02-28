package com.blabla.posts.api.domain.seedwork;

import org.springframework.lang.NonNull;
import java.util.*;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    private transient final List<DomainEvent> domainEvents = new ArrayList<>();

    protected <T extends DomainEvent> T addDomainEvent(@NonNull T event) {
        Objects.requireNonNull(event, "Domain event must not be null!");

        this.domainEvents.add(event);
        return event;
    }

    public <T extends DomainEvent> void removeDomainEvent(@NonNull T event) {
        Objects.requireNonNull(event, "Domain event must not be null!");

        domainEvents.remove(event);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    public Collection<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}
