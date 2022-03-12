package com.blabla.posts.api.application.dtos;

import com.blabla.posts.common.utils.ContentsUtils;
import com.blabla.posts.common.utils.FileUrlUtils;
import java.math.BigDecimal;
import java.util.Date;
import lombok.NonNull;

public record PostPreviewDTO(
    @NonNull String id,
    @NonNull BigDecimal latitude,
    @NonNull BigDecimal longitude,
    @NonNull String title,
    @NonNull String contentsSnippet,
    String thumbnailUrl,
    @NonNull Double distanceKm,
    @NonNull Integer joinedUsers,
    @NonNull Date createdAt,
    @NonNull Date updatedAt
) {
    public static PostPreviewDTO fromParameters(
        @NonNull String id,
        @NonNull BigDecimal latitude,
        @NonNull BigDecimal longitude,
        @NonNull String title,
        @NonNull String contents,
        String thumbnailDomain,
        String thumbnailBlobFileName,
        @NonNull Double distanceKm,
        @NonNull Integer joinedUsers,
        @NonNull Date createdAt,
        @NonNull Date updatedAt
    ) {
        return new PostPreviewDTO(
            id,
            latitude,
            longitude,
            title,
            ContentsUtils.getSnippetByContents(contents),
            FileUrlUtils.getUrlByDomainAndFilename(thumbnailDomain, thumbnailBlobFileName),
            distanceKm,
            joinedUsers,
            createdAt,
            updatedAt
        );
    }
}
