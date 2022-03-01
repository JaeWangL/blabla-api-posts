package com.blabla.posts.api.application.infrastructure.commandbus;

import an.awesome.pipelinr.Command;

public interface CommandBus {
    <R, C extends Command<R>> R send(C command);
}
