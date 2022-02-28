package com.blabla.posts.api.domain.aggregatesmodel.writer;

import com.blabla.posts.api.domain.aggregatesmodel.writer.snapshot.WriterSnapshot;
import com.blabla.posts.api.domain.seedwork.AggregateRoot;
import com.github.ksuid.Ksuid;
import java.util.Objects;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class Writer extends AggregateRoot<WriterId> {
    @Getter
    private final DeviceInfo device;

    private Writer(
        @NonNull WriterId id,
        @NonNull DeviceInfo device
    ) {
        this.id = Objects.requireNonNull(id, "Writer id cannot be null");
        this.device = Objects.requireNonNull(device, "Device Info cannot be null");
    }

    public Writer(@NonNull DeviceInfo device) {
        this(WriterId.of(Ksuid.newKsuid().toString()), device);
    }

    @NonNull
    @Override
    public WriterSnapshot snapshot() {
        return WriterSnapshot.builder()
            .id(id.getValue())
            .deviceType(device.getDeviceType().getType())
            .deviceId(device.getDeviceId())
            .build();
    }

    @NonNull
    public static Writer rehydrate(@NonNull WriterSnapshot snapshot) {
        Objects.requireNonNull(snapshot, "Snapshot cannot be null");
        return new Writer(
            WriterId.of(snapshot.getId()),
            DeviceInfo.of(DeviceType.of(snapshot.getDeviceType()), snapshot.getDeviceId())
        );
    }
}
