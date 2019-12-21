package com.mobtally.core.api;

import com.google.gson.JsonElement;
import com.mobtally.core.serialization.FromJsonHelper;
import org.apache.commons.lang3.StringUtils;

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

    private final FromJsonHelper fromApiJsonHelper;

    public JsonCommand(final Long commandId, final String jsonCommand, JsonElement jsonElement,
                       final FromJsonHelper fromJsonHelper) {
        this.commandId = commandId;
        this.jsonCommand = jsonCommand;
        this.parsedCommand = jsonElement;
        this.fromApiJsonHelper = fromJsonHelper;
    }

    public static JsonCommand from(final String jsonCommand, final JsonElement parsedCommand, final FromJsonHelper jsonHelper) {
        return new JsonCommand(null, jsonCommand, parsedCommand, jsonHelper);
    }

    public String stringValueOfParameterNamed(final String parameterName) {
        final String value = this.fromApiJsonHelper.extractStringNamed(parameterName, this.parsedCommand);
        return StringUtils.defaultIfEmpty(value, "");
    }
}
