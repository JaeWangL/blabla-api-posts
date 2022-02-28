package com.blabla.posts.api.domain.aggregatesmodel.writer;

import com.blabla.posts.api.domain.seedwork.ValueObject;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class DeviceInfo extends ValueObject {
    private final DeviceType deviceType;
    private final String deviceId;

    public static DeviceInfo of(DeviceType deviceType, String deviceId) {
        return new DeviceInfo(deviceType, deviceId);
    }

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(
            deviceType,
            deviceId
        );
    }
}
