package com.blabla.posts.api.infrastructure.entities.post;

import com.blabla.posts.api.infrastructure.entities.BaseEntity;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Getter
@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity {
    @Column(name = "writer_id", nullable = false)
    private String writerId;

    @Embedded
    private LocationModel location;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false, columnDefinition = "longtext")
    private String contents;

    @Embedded
    private ThumbnailModel thumbnail;

    @Column(name = "joined_users", nullable = false, columnDefinition = "int(11) default 0")
    private Integer joinedUsers;
}
