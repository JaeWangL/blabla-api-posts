package com.blabla.posts.api.domain.aggregatesmodel.post;

import com.blabla.posts.api.domain.aggregatesmodel.writer.WriterId;
import com.blabla.posts.api.domain.seedwork.ValueObject;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class NewPostData extends ValueObject {
    private final WriterId writerId;
    private final Location location;
    private final String title;
    private final String contents;
    private final Thumbnail thumbnail;

    @Builder
    private NewPostData(
        @NonNull WriterId writerId,
        @NonNull Location location,
        @NonNull String title,
        @NonNull String contents,
        Thumbnail thumbnail
    ) {
        this.writerId = Objects.requireNonNull(writerId, "Writer id cannot be null");
        this.location = Objects.requireNonNull(location, "Location cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.contents = Objects.requireNonNull(contents, "Contents cannot be null");
        this.thumbnail = thumbnail;
    }

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(writerId, location, title, contents, thumbnail);
    }
}
