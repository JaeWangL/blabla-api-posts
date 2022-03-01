package com.blabla.posts.api.infrastructure.repositories;

import com.blabla.posts.api.infrastructure.entities.writer.WriterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface WriterEntityRepository extends CrudRepository<WriterEntity, String> {
    Iterable<WriterEntity> findManyByDeviceDeviceTypeAndDeviceDeviceId(Short deviceType, String deviceId);
}
