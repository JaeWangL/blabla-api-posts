package com.blabla.posts.api.domain.aggregatesmodel.post;

import com.blabla.posts.api.domain.seedwork.ValueObject;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class Location extends ValueObject {
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String country;
    private final String state;
    private final String city;
    private final String street;
    private final String detailAddress;
    private final String zipCode;

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(
            latitude,
            longitude,
            country,
            state,
            city,
            street,
            detailAddress,
            zipCode
        );
    }
}
