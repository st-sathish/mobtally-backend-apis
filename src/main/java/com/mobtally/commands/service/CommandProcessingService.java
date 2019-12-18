package com.mobtally.commands.service;

import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapper;
import com.mobtally.core.api.JsonCommand;

public interface CommandProcessingService {

    CommandProcessingResult processCommand(CommandWrapper wrapper, JsonCommand command);
}
