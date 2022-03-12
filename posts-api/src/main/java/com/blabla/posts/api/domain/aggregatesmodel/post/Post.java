package com.blabla.posts.api.domain.aggregatesmodel.post;

import com.blabla.posts.api.domain.aggregatesmodel.post.snapshot.PostSnapshot;
import com.blabla.posts.api.domain.aggregatesmodel.writer.WriterId;
import com.blabla.posts.api.domain.events.PostCreatedDomainEvent;
import com.blabla.posts.api.domain.seedwork.AggregateRoot;
import com.github.ksuid.Ksuid;
import java.util.Objects;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class Post extends AggregateRoot<PostId> {
    private final WriterId writerId;
    private final Location location;
    @Getter
    private final String title;
    @Getter
    private final String contents;
    @Getter
    private final Thumbnail thumbnail;
    private final Integer joinedUsers;

    private Post(
        @NonNull PostId postId,
        @NonNull WriterId writerId,
        @NonNull Location location,
        @NonNull String title,
        @NonNull String contents,
        Thumbnail thumbnail,
        Integer joinedUsers
    ) {
        this.id = Objects.requireNonNull(postId, "Post id cannot be null");
        this.writerId = Objects.requireNonNull(writerId, "Writer id cannot be null");
        this.location = Objects.requireNonNull(location, "Location cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.contents = Objects.requireNonNull(contents, "Contents cannot be null");
        this.thumbnail = thumbnail;
        this.joinedUsers = joinedUsers;
    }

    @NonNull
    @Override
    public PostSnapshot snapshot() {
        return PostSnapshot.builder()
            .id(id.getValue())
            .writerId(writerId.getValue())
            .latitude(location.getLatitude())
            .longitude(location.getLongitude())
            .country(location.getCountry())
            .state(location.getState())
            .city(location.getCity())
            .street(location.getStreet())
            .detailAddress(location.getDetailAddress())
            .zipCode(location.getZipCode())
            .title(title)
            .contents(contents)
            .thumbnailDomain(thumbnail.getThumbnailDomain())
            .blobFileName(thumbnail.getBlobFileName())
            .originalFileName(thumbnail.getOriginalFileName())
            .joinedUsers(joinedUsers)
            .build();
    }

    @NonNull
    public static Post rehydrate(@NonNull PostSnapshot snapshot) {
        Objects.requireNonNull(snapshot, "Snapshot cannot be null");
        return new Post(
            PostId.of(snapshot.getId()),
            WriterId.of(snapshot.getWriterId()),
            Location.builder()
                .latitude(snapshot.getLatitude())
                .longitude(snapshot.getLongitude())
                .country(snapshot.getCountry())
                .state(snapshot.getState())
                .city(snapshot.getCity())
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
                .build(),
            snapshot.getJoinedUsers()
        );
    }

    @NonNull
    public static Post create(@NonNull NewPostData newData) {
        Objects.requireNonNull(newData, "New post data cannot be null");
        var newDomain = new Post(PostId.of(Ksuid.newKsuid().toString()), newData.getWriterId(), newData.getLocation(), newData.getTitle(), newData.getContents(), newData.getThumbnail(), 0);

        newDomain.addPostCreatedDomainEvent(newData);
        return newDomain;
    }

    private void addPostCreatedDomainEvent(NewPostData newData) {
        var orderStartedDomainEvent = new PostCreatedDomainEvent(
            this
        );

        this.addDomainEvent(orderStartedDomainEvent);
    }
}
