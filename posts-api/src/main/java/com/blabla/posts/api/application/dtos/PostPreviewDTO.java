package com.blabla.posts.api.application.dtos;

import com.blabla.posts.common.utils.ContentsUtils;
import com.blabla.posts.common.utils.FileUrlUtils;
import java.util.Date;
import lombok.NonNull;

public record PostPreviewDTO(
    @NonNull String id,
    @NonNull String title,
    @NonNull String contentsSnippet,
    String thumbnailUrl,
    @NonNull Date createdAt,
    @NonNull Date updatedAt
) {
    public static PostPreviewDTO fromParameters(
        @NonNull String id,
        @NonNull String title,
        @NonNull String contents,
        String thumbnailDomain,
        String thumbnailBlobFileName,
        @NonNull Date createdAt,
        @NonNull Date updatedAt
    ) {
        return new PostPreviewDTO(
            id,
            title,
            ContentsUtils.getSnippetByContents(contents),
            FileUrlUtils.getUrlByDomainAndFilename(thumbnailDomain, thumbnailBlobFileName),
            createdAt,
            updatedAt
        );
    }
}
