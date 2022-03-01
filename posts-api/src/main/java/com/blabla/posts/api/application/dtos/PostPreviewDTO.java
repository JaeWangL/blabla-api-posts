package com.blabla.posts.api.application.dtos;

import com.blabla.posts.api.domain.aggregatesmodel.post.Post;
import com.blabla.posts.common.utils.ContentsUtils;
import com.blabla.posts.common.utils.FileUrlUtils;

public record PostPreviewDTO(
    String id,
    String title,
    String contentsSnippet,
    String thumbnailUrl
) {
    public static PostPreviewDTO fromParameters(
        String id,
        String title,
        String contents,
        String thumbnailDomain,
        String thumbnailBlobFileName
    ) {
        return new PostPreviewDTO(
            id,
            title,
            ContentsUtils.getSnippetByContents(contents),
            FileUrlUtils.getUrlByDomainAndFilename(thumbnailDomain, thumbnailBlobFileName)
        );
    }
}
