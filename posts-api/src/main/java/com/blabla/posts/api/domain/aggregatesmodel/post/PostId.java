package com.blabla.posts.api.domain.aggregatesmodel.post;

import com.blabla.posts.api.domain.seedwork.Identifier;
import lombok.ToString;
import org.springframework.lang.NonNull;

@ToString
public class PostId extends Identifier {
    private PostId(String id) {
        super(id);
    }

    public static PostId of(@NonNull String value) {
        return new PostId(value);
    }
}
