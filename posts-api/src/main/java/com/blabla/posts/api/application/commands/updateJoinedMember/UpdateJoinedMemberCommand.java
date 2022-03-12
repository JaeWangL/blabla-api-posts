package com.blabla.posts.api.application.commands.updateJoinedMember;

import an.awesome.pipelinr.Command;
import javax.validation.constraints.NotNull;

public record UpdateJoinedMemberCommand(
    @NotNull String postId,
    @NotNull Integer newMemberCount
) implements Command<Boolean>  {
}
