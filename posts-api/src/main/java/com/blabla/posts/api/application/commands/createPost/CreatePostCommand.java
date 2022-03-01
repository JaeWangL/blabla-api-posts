package com.blabla.posts.api.application.commands.createPost;

import an.awesome.pipelinr.Command;
import com.blabla.posts.api.application.dtos.PostCreatedDTO;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record CreatePostCommand(
    @NotNull Short deviceType,
    @NotEmpty String deviceId,
    @NotNull BigDecimal latitude,
    @NotNull BigDecimal longitude,
    String country,
    String state,
    String city,
    String street,
    String detailAddress,
    String zipCode,
    @NotEmpty String title,
    @NotEmpty String contents,
    String thumbnailUrl,
    String originalFileName
) implements Command<PostCreatedDTO> {
}
