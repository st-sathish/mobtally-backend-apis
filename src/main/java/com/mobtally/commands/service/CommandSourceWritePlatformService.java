package com.mobtally.commands.service;

import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapper;

public interface CommandSourceWritePlatformService {

    CommandProcessingResult logCommandSource(CommandWrapper wrapper);
}
