package com.blabla.posts.api.application.dtos;

import com.blabla.posts.api.infrastructure.entities.post.PostEntity;
import com.blabla.posts.common.utils.FileUrlUtils;
import java.math.BigDecimal;
import java.util.Date;
import lombok.NonNull;

public record PostDetailDTO(
    @NonNull String id,
    @NonNull BigDecimal latitude,
    @NonNull BigDecimal longitude,
    @NonNull String title,
    @NonNull String contents,
    String thumbnailUrl,
    @NonNull Integer joinedUsers,
    @NonNull Date createdAt,
    @NonNull Date updatedAt
) {
    public static PostDetailDTO fromEntity(@NonNull PostEntity entity) {
        return new PostDetailDTO(
            entity.getId(),
            entity.getLocation().getLatitude(),
            entity.getLocation().getLongitude(),
            entity.getTitle(),
            entity.getContents(),
            FileUrlUtils.getUrlByDomainAndFilename(entity.getThumbnail().getThumbnailDomain(), entity.getThumbnail().getBlobFileName()),
            entity.getJoinedUsers(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
