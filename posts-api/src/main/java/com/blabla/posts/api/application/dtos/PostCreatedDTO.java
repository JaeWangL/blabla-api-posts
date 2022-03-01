package com.blabla.posts.api.application.dtos;

import com.blabla.posts.api.domain.aggregatesmodel.post.Post;
import com.blabla.posts.api.domain.aggregatesmodel.writer.Writer;

public record PostCreatedDTO(
    String id,
    Short writerDeviceType,
    String writerDeviceId
) {
    public static PostCreatedDTO fromDomain(Post post, Writer writer) {
        return new PostCreatedDTO(
            post.getId().getValue(),
            writer.getDevice().getDeviceType().getType(),
            writer.getDevice().getDeviceId()
        );
    }
}
