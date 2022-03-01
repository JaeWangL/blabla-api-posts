package com.blabla.posts.api.application.commands.createPost;

import com.blabla.posts.api.application.dtos.PostCreatedDTO;
import com.blabla.posts.api.application.integrationevents.events.PostCreatedIntegrationEvent;
import com.blabla.posts.api.configs.KafkaTopics;
import com.blabla.posts.api.domain.aggregatesmodel.post.Location;
import com.blabla.posts.api.domain.aggregatesmodel.post.NewPostData;
import com.blabla.posts.api.domain.aggregatesmodel.post.Post;
import com.blabla.posts.api.domain.aggregatesmodel.post.PostRepository;
import com.blabla.posts.api.domain.aggregatesmodel.post.Thumbnail;
import com.blabla.posts.api.domain.aggregatesmodel.writer.DeviceInfo;
import com.blabla.posts.api.domain.aggregatesmodel.writer.DeviceType;
import com.blabla.posts.api.domain.aggregatesmodel.writer.NewWriterData;
import com.blabla.posts.api.domain.aggregatesmodel.writer.Writer;
import com.blabla.posts.api.domain.aggregatesmodel.writer.WriterId;
import com.blabla.posts.api.domain.aggregatesmodel.writer.WriterRepository;
import com.blabla.posts.common.outbox.IntegrationEventLogService;
import com.blabla.posts.common.shared.CommandHandler;
import an.awesome.pipelinr.Command;
import com.github.ksuid.Ksuid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@CommandHandler
@RequiredArgsConstructor
public class CreatePostCommandHandler implements Command.Handler<CreatePostCommand, PostCreatedDTO> {
    private static final Logger logger = LoggerFactory.getLogger(CreatePostCommandHandler.class);
    private final KafkaTopics topics;
    private final IntegrationEventLogService eventLogService;
    private final WriterRepository writerRepo;
    private final PostRepository postRepo;

    @Transactional
    @Override
    public PostCreatedDTO handle(CreatePostCommand command) {
        // Add Integration event to notifying new post
        eventLogService.saveEvent(new PostCreatedIntegrationEvent(command.latitude(), command.longitude()), topics.getCreatedPosts());

        final var writerId = WriterId.of(Ksuid.newKsuid().toString());
        final var writer = createWriter(writerId, command.deviceType(), command.deviceId());
        final var post = createPost(writerId, command);

        logger.info("Creating new Post");

        writerRepo.save(writer);
        postRepo.save(post);

        return PostCreatedDTO.fromDomain(post, writer);
    }

    private Writer createWriter(WriterId writerId, Short deviceType, String deviceId) {
        final var newData = NewWriterData.builder()
            .writerId(writerId)
            .device(DeviceInfo.builder()
                .deviceType(DeviceType.of(deviceType))
                .deviceId(deviceId)
                .build())
            .build();

        return Writer.create(newData);
    }

    private Post createPost(WriterId writerId, CreatePostCommand command) {
        String thumbnailDomain = null;
        String thumbnailBlobFileName = null;
        if (command.thumbnailUrl() != null && command.thumbnailUrl().matches("^(http?|https)://.*$")) {
            thumbnailDomain = FilenameUtils.getPath(command.thumbnailUrl());
            thumbnailBlobFileName = FilenameUtils.getName(command.thumbnailUrl());
        }

        final var newData = NewPostData.builder()
            .writerId(writerId)
            .location(Location.builder()
                .latitude(command.latitude())
                .longitude(command.longitude())
                .country(command.country())
                .state(command.state())
                .city(command.city())
                .street(command.street())
                .detailAddress(command.detailAddress())
                .zipCode(command.zipCode())
                .build())
            .title(command.title())
            .contents(command.contents())
            .thumbnail(Thumbnail.builder()
                .thumbnailDomain(thumbnailDomain)
                .blobFileName(thumbnailBlobFileName)
                .originalFileName(command.originalFileName())
                .build())
            .build();

        return Post.create(newData);
    }
}
