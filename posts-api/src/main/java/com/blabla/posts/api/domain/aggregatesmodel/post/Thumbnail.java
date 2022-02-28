package com.blabla.posts.api.domain.aggregatesmodel.post;

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
public class Thumbnail extends ValueObject {
    private final String thumbnailDomain;
    private final String blobFileName;
    private final String originalFileName;

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(
            thumbnailDomain,
            blobFileName,
            originalFileName
        );
    }
}
