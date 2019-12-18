package com.mobtally.core;

import com.mobtally.core.api.JsonCommand;

public interface NewCommandSourceHandler {

    CommandProcessingResult processCommand(JsonCommand command);
}
