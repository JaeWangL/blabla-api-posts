package com.blabla.posts.api.domain.aggregatesmodel.writer.snapshot;

import com.blabla.posts.api.domain.seedwork.Snapshot;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WriterSnapshot implements Snapshot {
    private final String id;
    private final Short deviceType;
    private final String deviceId;
}
