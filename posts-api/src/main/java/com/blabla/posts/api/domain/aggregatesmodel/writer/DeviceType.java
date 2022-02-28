package com.blabla.posts.api.domain.aggregatesmodel.writer;

import com.blabla.posts.api.domain.exceptions.PostDomainException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DeviceType {
    None((short) 0),
    Android((short) 1),
    iOS((short) 2);

    @Getter
    private final Short type;

    public static DeviceType of(Short type) {
        return Stream.of(values()).filter(s -> s.getType().equals(type))
            .findFirst()
            .orElseThrow(() -> new PostDomainException("Invalid device type: %s".formatted(type)));
    }
}
