package com.blabla.posts.api.infrastructure.mappers;

import com.blabla.posts.api.domain.aggregatesmodel.writer.Writer;
import com.blabla.posts.api.domain.aggregatesmodel.writer.snapshot.WriterSnapshot;
import com.blabla.posts.api.infrastructure.entities.writer.DeviceInfoModel;
import com.blabla.posts.api.infrastructure.entities.writer.WriterEntity;
import org.springframework.stereotype.Component;

@Component
public class WriterEntityMapper implements EntityMapper<WriterEntity, Writer> {
    @Override
    public WriterEntity toEntity(Writer domain) {
        var snapshot = domain.snapshot();

        return WriterEntity.builder()
            .id(snapshot.getId())
            .device(DeviceInfoModel.builder()
                .deviceType(snapshot.getDeviceType())
                .deviceId(snapshot.getDeviceId())
                .build())
            .build();
    }

    @Override
    public Writer fromEntity(WriterEntity entity) {
        return Writer.rehydrate(WriterSnapshot.builder()
            .id(entity.getId())
            .deviceType(entity.getDevice().getDeviceType())
            .deviceId(entity.getDevice().getDeviceId())
            .build());
    }
}
