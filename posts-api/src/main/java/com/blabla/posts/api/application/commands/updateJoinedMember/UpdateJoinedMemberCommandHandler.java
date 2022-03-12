package com.blabla.posts.api.application.commands.updateJoinedMember;

import an.awesome.pipelinr.Command;
import com.blabla.posts.api.domain.aggregatesmodel.post.PostId;
import com.blabla.posts.api.domain.aggregatesmodel.post.PostRepository;
import com.blabla.posts.common.shared.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@CommandHandler
@RequiredArgsConstructor
public class UpdateJoinedMemberCommandHandler implements Command.Handler<UpdateJoinedMemberCommand, Boolean> {
    private final PostRepository postRepo;

    @Transactional
    @Override
    public Boolean handle(UpdateJoinedMemberCommand command) {
        postRepo.findById(PostId.of(command.postId()))
            .ifPresent(post -> {
                post.changeJoinedMember(command.newMemberCount());
                postRepo.save(post);
            });

        return true;
    }
}
