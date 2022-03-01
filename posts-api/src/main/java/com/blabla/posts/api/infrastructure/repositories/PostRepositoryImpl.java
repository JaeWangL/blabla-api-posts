package com.blabla.posts.api.infrastructure.repositories;

import com.blabla.posts.api.domain.aggregatesmodel.post.Post;
import com.blabla.posts.api.domain.aggregatesmodel.post.PostId;
import com.blabla.posts.api.domain.aggregatesmodel.post.PostRepository;
import com.blabla.posts.api.infrastructure.mappers.PostEntityMapper;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostEntityRepository entityRepo;
    private final ApplicationEventPublisher eventPublisher;
    private final PostEntityMapper entityMapper;
    private final EntityManager entityManager;

    @Override
    public Post save(@NonNull Post aggregateRoot) {
        var newEntity = entityMapper.toEntity(aggregateRoot);
        entityManager.merge(newEntity);
        aggregateRoot.domainEvents().forEach(eventPublisher::publishEvent);
        aggregateRoot.clearDomainEvents();

        return entityMapper.fromEntity(newEntity);
    }

    @Override
    public Optional<Post> findById(@NonNull PostId id) {
        return entityRepo.findById(id.getValue())
            .map(entityMapper::fromEntity);
    }
}
