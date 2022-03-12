package com.blabla.posts.api.domain.aggregatesmodel.post.snapshot;

import com.blabla.posts.api.domain.seedwork.Snapshot;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostSnapshot implements Snapshot {
    private final String id;
    private final String writerId;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String country;
    private final String city;
    private final String state;
    private final String street;
    private final String detailAddress;
    private final String zipCode;
    private final String title;
    private final String contents;
    private final String thumbnailDomain;
    private final String blobFileName;
    private final String originalFileName;
    private final Integer joinedUsers;
}
