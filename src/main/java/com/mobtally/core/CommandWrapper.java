package com.mobtally.core;

public class CommandWrapper {

    private final String json;
    private final String actionName;
    private final String entityName;

    public CommandWrapper(final String json,
                          final String entityName,
                          final String actionName) {
        this.json = json;
        this.entityName = entityName;
        this.actionName = actionName;
    }

    public String actionName() {
        return actionName;
    }

    public String entityName() {
        return entityName;
    }

    public String getJson() {
        return json;
    }
}
