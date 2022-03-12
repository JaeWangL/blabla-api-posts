package com.blabla.posts.api.application.integrationevents.eventhandling;

import an.awesome.pipelinr.Pipeline;
import com.blabla.posts.api.application.commands.updateJoinedMember.UpdateJoinedMemberCommand;
import com.blabla.posts.api.application.integrationevents.events.LeavedRoomMemberIntegrationEvent;
import com.blabla.posts.common.shared.EventHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

@EventHandler
@RequiredArgsConstructor
public class LeavedRoomMemberIntegrationEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(LeavedRoomMemberIntegrationEventHandler.class);
    private final Pipeline pipeline;

    @KafkaListener(
        groupId = "${app.kafka.group.leavedRoomMember}",
        topics = "${spring.kafka.consumer.topic.leavedRoomMember}"
    )
    public void handle(LeavedRoomMemberIntegrationEvent event) {
        logger.info("Handling integration event: {} - ({})", event.getId(), event.getClass().getSimpleName());

        pipeline.send(new UpdateJoinedMemberCommand(event.getPostId(), event.getUpdatedMemberCount()));
    }
}
