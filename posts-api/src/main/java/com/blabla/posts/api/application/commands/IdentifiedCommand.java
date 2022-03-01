package com.blabla.posts.api.application.commands;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class IdentifiedCommand<T extends Command<R>, R> implements Command<R> {
    private final T command;
    private final String id;
}
