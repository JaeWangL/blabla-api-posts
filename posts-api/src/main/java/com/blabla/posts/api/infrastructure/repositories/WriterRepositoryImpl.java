package com.blabla.posts.api.infrastructure.repositories;

import com.blabla.posts.api.domain.aggregatesmodel.writer.DeviceInfo;
import com.blabla.posts.api.domain.aggregatesmodel.writer.Writer;
import com.blabla.posts.api.domain.aggregatesmodel.writer.WriterId;
import com.blabla.posts.api.domain.aggregatesmodel.writer.WriterRepository;
import com.blabla.posts.api.infrastructure.mappers.WriterEntityMapper;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

@Repository
@AllArgsConstructor
public class WriterRepositoryImpl implements WriterRepository {
    private final WriterEntityRepository entityRepo;
    private final ApplicationEventPublisher eventPublisher;
    private final WriterEntityMapper entityMapper;
    private final EntityManager entityManager;

    @Override
    public Writer save(@NonNull Writer aggregateRoot) {
        var newEntity = entityMapper.toEntity(aggregateRoot);
        entityManager.merge(newEntity);
        aggregateRoot.domainEvents().forEach(eventPublisher::publishEvent);
        aggregateRoot.clearDomainEvents();

        return entityMapper.fromEntity(newEntity);
    }

    @Override
    public Optional<Writer> findById(@NonNull WriterId id) {
        return entityRepo.findById(id.getValue())
            .map(entityMapper::fromEntity);
    }

    @Override
    public List<Writer> findManyByDevice(@NonNull DeviceInfo device) {
        var iterable = entityRepo.findManyByDeviceDeviceTypeAndDeviceDeviceId(
            device.getDeviceType().getType(),
            device.getDeviceId());

        return StreamSupport.stream(iterable.spliterator(), false)
            .map(entityMapper::fromEntity)
            .collect(Collectors.toList());
    }
}
