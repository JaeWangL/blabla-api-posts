package com.blabla.posts.api.application.integrationevents.events;

import com.blabla.posts.common.eventhandling.IntegrationEvent;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostCreatedIntegrationEvent extends IntegrationEvent {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
