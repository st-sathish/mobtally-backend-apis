package com.mobtally.commands.service;

import com.google.gson.JsonElement;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapper;
import com.mobtally.core.api.JsonCommand;
import com.mobtally.core.serialization.FromJsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSourceWritePlatformServiceImpl implements CommandSourceWritePlatformService {

    private final CommandProcessingService commandProcessingService;

    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public CommandSourceWritePlatformServiceImpl(final CommandProcessingService commandProcessingService,
                                                 final FromJsonHelper fromApiJsonHelper) {
        this.commandProcessingService = commandProcessingService;
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    @Override
    public CommandProcessingResult logCommandSource(CommandWrapper wrapper) {
        final String json = wrapper.getJson();
        final JsonElement parsedCommand = this.fromApiJsonHelper.parse(json);
        final JsonCommand command = JsonCommand.from(json, parsedCommand);
        CommandProcessingResult result = commandProcessingService.processCommand(wrapper, command);
        return result;
    }
}
