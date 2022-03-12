package com.blabla.posts.api.infrastructure.mappers;

import com.blabla.posts.api.domain.aggregatesmodel.post.Post;
import com.blabla.posts.api.domain.aggregatesmodel.post.snapshot.PostSnapshot;
import com.blabla.posts.api.infrastructure.entities.post.LocationModel;
import com.blabla.posts.api.infrastructure.entities.post.PostEntity;
import com.blabla.posts.api.infrastructure.entities.post.ThumbnailModel;
import org.springframework.stereotype.Component;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class PostEntityMapper implements EntityMapper<PostEntity, Post> {
    @Override
    public PostEntity toEntity(Post domain) {
        var snapshot = domain.snapshot();

        return PostEntity.builder()
            .id(snapshot.getId())
            .writerId(snapshot.getWriterId())
            .location(LocationModel.builder()
                .latitude(snapshot.getLatitude())
                .longitude(snapshot.getLongitude())
                .country(snapshot.getCountry() != null ? snapshot.getCountry() : "")
                .city(snapshot.getCity() != null ? snapshot.getCity() : "")
                .state(snapshot.getState() != null ? snapshot.getState() : "")
                .street(snapshot.getStreet() != null ? snapshot.getStreet() : "")
                .detailAddress(snapshot.getDetailAddress() != null ? snapshot.getDetailAddress() : "")
                .zipCode(snapshot.getZipCode() != null ? snapshot.getZipCode() : "")
                .build())
            .title(snapshot.getTitle())
            .contents(snapshot.getContents())
            .thumbnail(ThumbnailModel.builder()
                .thumbnailDomain(snapshot.getThumbnailDomain() != null ? snapshot.getThumbnailDomain() : "")
                .blobFileName(snapshot.getBlobFileName() != null ? snapshot.getBlobFileName() : "")
                .originalFileName(snapshot.getOriginalFileName() != null ? snapshot.getOriginalFileName() : "")
                .build())
            .joinedUsers(snapshot.getJoinedUsers())
            .build();
    }

    @Override
    public Post fromEntity(PostEntity entity) {
        return Post.rehydrate(PostSnapshot.builder()
            .id(entity.getId())
            .writerId(entity.getWriterId())
            .latitude(entity.getLocation().getLatitude())
            .longitude(entity.getLocation().getLongitude())
            .country(!isEmpty(entity.getLocation().getCountry()) ? entity.getLocation().getCountry() : null)
            .state(!isEmpty(entity.getLocation().getState()) ? entity.getLocation().getState() : null)
            .city(!isEmpty(entity.getLocation().getCity()) ? entity.getLocation().getCity() : null)
            .street(!isEmpty(entity.getLocation().getStreet()) ? entity.getLocation().getStreet() : null)
            .detailAddress(!isEmpty(entity.getLocation().getDetailAddress()) ? entity.getLocation().getDetailAddress() : null)
            .zipCode(!isEmpty(entity.getLocation().getZipCode()) ? entity.getLocation().getZipCode() : null)
            .title(entity.getTitle())
            .contents(entity.getContents())
            .thumbnailDomain(!isEmpty(entity.getThumbnail().getThumbnailDomain()) ? entity.getThumbnail().getThumbnailDomain() : null)
            .blobFileName(!isEmpty(entity.getThumbnail().getBlobFileName()) ? entity.getThumbnail().getBlobFileName() : null)
            .originalFileName(!isEmpty(entity.getThumbnail().getOriginalFileName()) ? entity.getThumbnail().getOriginalFileName() : null)
            .joinedUsers(entity.getJoinedUsers())
            .build());
    }
}
