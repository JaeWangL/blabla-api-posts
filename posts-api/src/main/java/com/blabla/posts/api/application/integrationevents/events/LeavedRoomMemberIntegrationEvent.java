package com.blabla.posts.api.application.integrationevents.events;

import com.blabla.posts.common.eventhandling.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LeavedRoomMemberIntegrationEvent extends IntegrationEvent {
    private String postId;
    private Integer updatedMemberCount;
}
