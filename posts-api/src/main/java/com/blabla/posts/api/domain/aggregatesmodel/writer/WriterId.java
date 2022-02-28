package com.blabla.posts.api.domain.aggregatesmodel.writer;

import com.blabla.posts.api.domain.seedwork.Identifier;
import lombok.ToString;
import org.springframework.lang.NonNull;

@ToString
public class WriterId extends Identifier {
    private WriterId(String id) {
        super(id);
    }

    public static WriterId of(@NonNull String value) {
        return new WriterId(value);
    }
}
