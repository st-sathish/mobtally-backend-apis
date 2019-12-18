package com.mobtally.core.api;

import com.google.gson.JsonElement;

/**
 * Immutable representation of a command.
 *
 * Wraps the provided JSON with convenience functions for extracting parameter
 * values and checking for changes against an existing value.
 */
public final class JsonCommand {

    private final String jsonCommand;

    private final Long commandId;

    private final JsonElement parsedCommand;

    public JsonCommand(final Long commandId, final String jsonCommand, JsonElement jsonElement) {
        this.commandId = commandId;
        this.jsonCommand = jsonCommand;
        this.parsedCommand = jsonElement;
    }

    public static JsonCommand from(final String jsonCommand, final JsonElement parsedCommand) {
        return new JsonCommand(null, jsonCommand, parsedCommand);
    }
}
