package com.blabla.posts.api.infrastructure.entities.post;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Embeddable
public class LocationModel {
    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "country", nullable = false, columnDefinition = "varchar(255) default ''")
    private String country;

    @Column(name = "state", nullable = false, columnDefinition = "varchar(255) default ''")
    private String state;

    @Column(name = "city", nullable = false, columnDefinition = "varchar(255) default ''")
    private String city;

    @Column(name = "street", nullable = false, columnDefinition = "varchar(255) default ''")
    private String street;

    @Column(name = "detail_address", nullable = false, columnDefinition = "varchar(255) default ''")
    private String detailAddress;

    @Column(name = "zip_code", nullable = false, columnDefinition = "varchar(255) default ''")
    private String zipCode;
}
