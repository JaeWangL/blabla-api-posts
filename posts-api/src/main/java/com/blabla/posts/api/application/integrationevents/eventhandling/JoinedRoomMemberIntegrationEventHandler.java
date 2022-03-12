package com.blabla.posts.api.application.integrationevents.eventhandling;

import an.awesome.pipelinr.Pipeline;
import com.blabla.posts.api.application.commands.updateJoinedMember.UpdateJoinedMemberCommand;
import com.blabla.posts.api.application.integrationevents.events.JoinedRoomMemberIntegrationEvent;
import com.blabla.posts.common.shared.EventHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

@EventHandler
@RequiredArgsConstructor
public class JoinedRoomMemberIntegrationEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(JoinedRoomMemberIntegrationEventHandler.class);
    private final Pipeline pipeline;

    @KafkaListener(
        groupId = "${app.kafka.group.joinedRoomMember}",
        topics = "${spring.kafka.consumer.topic.joinedRoomMember}"
    )
    public void handle(JoinedRoomMemberIntegrationEvent event) {
        logger.info("Handling integration event: {} - ({})", event.getId(), event.getClass().getSimpleName());

        pipeline.send(new UpdateJoinedMemberCommand(event.getPostId(), event.getUpdatedMemberCount()));
    }
}
