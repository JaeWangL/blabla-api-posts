package com.blabla.posts.api.infrastructure.entities.post;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Embeddable
public class ThumbnailModel {
    @Column(name = "thumbnail_domain", nullable = false, columnDefinition = "varchar(255) default ''")
    private String thumbnailDomain;

    @Column(name = "thumbnail_blob_filename", nullable = false, columnDefinition = "varchar(255) default ''")
    private String blobFileName;

    @Column(name = "thumbnail_original_filename", nullable = false, columnDefinition = "varchar(255) default ''")
    private String originalFileName;
}
