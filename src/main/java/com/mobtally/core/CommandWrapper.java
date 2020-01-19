package com.mobtally.core;

public class CommandWrapper {

    private final String json;
    private final String actionName;
    private final String entityName;
    private final Long companyId;

    public CommandWrapper(final String json,
                          final String entityName,
                          final String actionName) {
        this.json = json;
        this.entityName = entityName;
        this.actionName = actionName;
        this.companyId = null;
    }

    public CommandWrapper(final String json,
                          final String entityName,
                          final String actionName,
                          final Long companyId) {
        this.json = json;
        this.entityName = entityName;
        this.actionName = actionName;
        this.companyId = companyId;
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

    public Long getCompanyId() {
        return companyId;
    }
}
