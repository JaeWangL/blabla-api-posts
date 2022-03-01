package com.blabla.posts.api.infrastructure.entities.writer;

import com.blabla.posts.api.infrastructure.entities.BaseEntity;
import com.blabla.posts.api.infrastructure.entities.post.LocationModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Getter
@Entity
@Table(name = "writers")
public class WriterEntity extends BaseEntity {
    @Embedded
    private DeviceInfoModel device;
}
