package com.blabla.posts.api.domain.aggregatesmodel.post;

import com.blabla.posts.api.domain.aggregatesmodel.post.snapshot.PostSnapshot;
import com.blabla.posts.api.domain.events.PostCreatedDomainEvent;
import com.blabla.posts.api.domain.seedwork.AggregateRoot;
import com.github.ksuid.Ksuid;
import java.util.Objects;
import org.springframework.lang.NonNull;

public class Post extends AggregateRoot<PostId> {
    private final Location location;
    private final String title;
    private final String contents;
    private final Thumbnail thumbnail;

    private Post(
        @NonNull PostId postId,
        @NonNull Location location,
        @NonNull String title,
        @NonNull String contents,
        Thumbnail thumbnail
    ) {
        this.id = Objects.requireNonNull(postId, "Post id cannot be null");
        this.location = Objects.requireNonNull(location, "Location cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.contents = Objects.requireNonNull(contents, "Contents cannot be null");
        this.thumbnail = thumbnail;
    }

    @NonNull
    public static Post create(@NonNull NewPostData newData) {
        Objects.requireNonNull(newData, "New post data cannot be null");
        var newDomain = new Post(PostId.of(Ksuid.newKsuid().toString()), newData.getLocation(), newData.getTitle(), newData.getContents(), newData.getThumbnail());

        newDomain.addPostCreatedDomainEvent(newData);
        return newDomain;
    }

    @NonNull
    @Override
    public PostSnapshot snapshot() {
        return PostSnapshot.builder()
            .id(id.getValue())
            .country(location.getCountry())
            .city(location.getCity())
            .state(location.getState())
            .street(location.getStreet())
            .detailAddress(location.getDetailAddress())
            .zipCode(location.getZipCode())
            .title(title)
            .contents(contents)
            .thumbnailDomain(thumbnail.getThumbnailDomain())
            .blobFileName(thumbnail.getBlobFileName())
            .originalFileName(thumbnail.getOriginalFileName())
            .build();
    }

    @NonNull
    public static Post rehydrate(@NonNull PostSnapshot snapshot) {
        Objects.requireNonNull(snapshot, "Snapshot cannot be null");
        return new Post(
            PostId.of(snapshot.getId()),
            Location.builder()
                .country(snapshot.getCountry())
                .city(snapshot.getCity())
                .state(snapshot.getState())
                .street(snapshot.getStreet())
                .detailAddress(snapshot.getDetailAddress())
                .zipCode(snapshot.getZipCode())
                .build(),
            snapshot.getTitle(),
            snapshot.getContents(),
            Thumbnail.builder()
                .thumbnailDomain(snapshot.getThumbnailDomain())
                .blobFileName(snapshot.getBlobFileName())
                .originalFileName(snapshot.getOriginalFileName())
                .build()
        );
    }

    private void addPostCreatedDomainEvent(NewPostData newData) {
        var orderStartedDomainEvent = new PostCreatedDomainEvent(
            this
        );

        this.addDomainEvent(orderStartedDomainEvent);
    }
}
