package com.blabla.posts.api.infrastructure.repositories;

import com.blabla.posts.api.infrastructure.entities.post.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostEntityRepository extends CrudRepository<PostEntity, String> {
}
