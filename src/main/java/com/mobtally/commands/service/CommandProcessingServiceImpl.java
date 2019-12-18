package com.mobtally.commands.service;

import com.mobtally.commands.provider.CommandHandlerProvider;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapper;
import com.mobtally.core.NewCommandSourceHandler;
import com.mobtally.core.api.JsonCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandProcessingServiceImpl implements CommandProcessingService {

    private final CommandHandlerProvider commandHandlerProvider;

    @Autowired
    private CommandProcessingServiceImpl(final CommandHandlerProvider commandHandlerProvider) {
        this.commandHandlerProvider = commandHandlerProvider;
    }

    @Override
    public CommandProcessingResult processCommand(final CommandWrapper wrapper,
                                                        final JsonCommand command) {
        NewCommandSourceHandler newCommandSourceHandler = findCommandHandler(wrapper);
        CommandProcessingResult commandProcessingResult = newCommandSourceHandler.processCommand(command);
        return commandProcessingResult;
    }

    private NewCommandSourceHandler findCommandHandler(final CommandWrapper wrapper) {
        NewCommandSourceHandler handler = null;
        handler = this.commandHandlerProvider.getHandler(wrapper.entityName(), wrapper.actionName());
        return handler;
    }
}
