package com.blabla.posts.api.application.queries;

import com.blabla.posts.api.application.dtos.PostDetailDTO;
import com.blabla.posts.api.application.dtos.PostPreviewDTO;
import com.blabla.posts.api.infrastructure.repositories.PostEntityRepository;
import com.blabla.posts.common.rest.error.NotFoundException;
import com.blabla.posts.common.shared.QueryHandler;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@QueryHandler
@RequiredArgsConstructor
public class PostQueriesImpl implements PostQueries {
    private final EntityManager entityManager;
    private final PostEntityRepository entityRepo;

    @Override
    public PostDetailDTO getPostById(String id) {
        return entityRepo.findById(id).map(
            PostDetailDTO::fromEntity
        ).orElseThrow(() -> new NotFoundException("Post not found id: %s".formatted(id)));
    }

    @Override
    public List<PostPreviewDTO> getPostsByDistance(BigDecimal currentLatitude, BigDecimal currentLongitude, double distanceInKm, Integer pageSize, Integer pageIndex) {
        var query = entityManager.createNativeQuery("""
        SELECT id, latitude, longitude, title, contents, thumbnail_domain, thumbnail_blob_filename, joined_users, created_at, updated_at, distance
        FROM (
            SELECT z.id,
                   z.latitude, z.longitude,
                   z.title, z.contents,
                   z.thumbnail_domain, z.thumbnail_blob_filename,
                   z.joined_users,
                   z.created_at, z.updated_at,
                   p.radius,
                   p.distance_unit
                       * DEGREES(ACOS(LEAST(1.0, COS(RADIANS(p.latpoint))
                       * COS(RADIANS(z.latitude))
                       * COS(RADIANS(p.longpoint - z.longitude))
                       + SIN(RADIANS(p.latpoint))
                       * SIN(RADIANS(z.latitude))))) AS distance
            FROM posts AS z
            JOIN (
                SELECT :currentLatitude AS latpoint, :currentLongitude AS longpoint,
                       :distanceInKm AS radius, 111.045 AS distance_unit
            ) AS p ON 1=1
            WHERE z.latitude
                BETWEEN p.latpoint  - (p.radius / p.distance_unit)
                    AND p.latpoint  + (p.radius / p.distance_unit)
            AND z.longitude
                BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
                    AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
        ) AS d
        WHERE distance <= radius
        ORDER BY distance
        LIMIT :pageSize OFFSET :pageIndex
        """)
            .setParameter("currentLatitude", currentLatitude)
            .setParameter("currentLongitude", currentLongitude)
            .setParameter("distanceInKm", distanceInKm)
            .setParameter("pageSize", pageSize)
            .setParameter("pageIndex", pageIndex);
        List<Object[]> results = query.getResultList();

        return results.stream()
            .map(r -> {
                var id = (String) r[0];
                var latitude = (BigDecimal) r[1];
                var longitude = (BigDecimal) r[2];
                var title = (String) r[3];
                var contents = (String) r[4];
                var thumbnailDomain = (String) r[5];
                var thumbnailBlobFileName = (String) r[6];
                var joinedUsers = (Integer) r[7];
                var createdAt = (Date) r[8];
                var updatedAt = (Date) r[9];
                var distance = (Double) r[10];

                return PostPreviewDTO.fromParameters(
                    id,
                    latitude,
                    longitude,
                    title,
                    contents,
                    thumbnailDomain,
                    thumbnailBlobFileName,
                    Math.round(distance * 1000),
                    joinedUsers,
                    createdAt,
                    updatedAt
                );
            }).collect(Collectors.toList());
    }
}
