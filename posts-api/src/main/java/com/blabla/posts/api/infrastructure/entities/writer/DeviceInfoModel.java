package com.blabla.posts.api.infrastructure.entities.writer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Embeddable
public class DeviceInfoModel {
    @Column(name = "device_type", nullable = false, columnDefinition = "smallint(6)")
    private Short deviceType;

    @Column(name = "device_Id", nullable = false)
    private String deviceId;
}
