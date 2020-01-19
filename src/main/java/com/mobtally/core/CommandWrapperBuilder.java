package com.mobtally.core;

public class CommandWrapperBuilder {

    private String json = "{}";
    private String action;
    private String entityName;
    private String entityId;
    private Long companyId;

    public CommandWrapper build() {
        return new CommandWrapper(this.json, this.entityName, this.action, this.companyId);
    }

    public CommandWrapperBuilder withJson(final String json) {
        this.json = json;
        return this;
    }

    public CommandWrapperBuilder withNoJsonBody() {
        this.json = null;
        return this;
    }

    public CommandWrapperBuilder createCompany(final String json) {
        this.action = "CREATE";
        this.entityName = "COMPANY";
        this.entityId = null;
        this.json = json;
        this.companyId = null;
        return this;
    }

    public CommandWrapperBuilder updateCompany(final String entityId, final Long companyId, final String json) {
        this.action = "UPDATE";
        this.entityName = "COMPANY";
        this.entityId = entityId;
        this.json = json;
        this.companyId = companyId;
        return this;
    }
}
