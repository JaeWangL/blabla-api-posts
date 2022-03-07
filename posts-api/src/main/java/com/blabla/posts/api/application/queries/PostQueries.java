package com.blabla.posts.api.application.queries;

import com.blabla.posts.api.application.dtos.PostDetailDTO;
import com.blabla.posts.api.application.dtos.PostPreviewDTO;
import java.math.BigDecimal;
import java.util.List;

public interface PostQueries {
    PostDetailDTO getPostById(String id);

    List<PostPreviewDTO> getPostsByDistance(BigDecimal currentLatitude, BigDecimal currentLongitude, double distanceInKm, Integer pageSize, Integer pageIndex);
}
