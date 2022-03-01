package com.blabla.posts.api.domain.aggregatesmodel.writer;

import com.blabla.posts.api.domain.seedwork.ValueObject;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class NewWriterData extends ValueObject {
    private final WriterId writerId;
    private final DeviceInfo device;

    @Builder
    private NewWriterData(
        @NonNull WriterId writerId,
        @NonNull DeviceInfo device
    ) {
        this.writerId = Objects.requireNonNull(writerId, "Writer id cannot be null");
        this.device = Objects.requireNonNull(device, "Device info cannot be null");
    }

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(writerId, device);
    }
}
