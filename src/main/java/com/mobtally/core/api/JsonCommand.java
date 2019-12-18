package com.mobtally.core.api;

/**
 * Immutable representation of a command.
 *
 * Wraps the provided JSON with convenience functions for extracting parameter
 * values and checking for changes against an existing value.
 */
public final class JsonCommand {

    private final String jsonCommand;

    private final Long commandId;

    public JsonCommand(final Long commandId, final String jsonCommand) {
        this.commandId = commandId;
        this.jsonCommand = jsonCommand;
    }
}
