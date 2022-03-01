package com.blabla.posts.api.domain.aggregatesmodel.post;

import com.blabla.posts.api.domain.seedwork.Repository;
import java.util.Optional;
import org.springframework.lang.NonNull;

public interface PostRepository extends Repository<Post> {
    Optional<Post> findById(@NonNull PostId id);
}
