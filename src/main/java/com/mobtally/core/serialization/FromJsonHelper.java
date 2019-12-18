package com.mobtally.core.serialization;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FromJsonHelper {

    private final Gson gsonConverter;
    private final JsonParserHelper helperDelegator;
    private final JsonParser parser;

    public FromJsonHelper() {
        this.gsonConverter = new Gson();
        this.helperDelegator = new JsonParserHelper();
        this.parser = new JsonParser();
    }

    public JsonElement parse(final String json) {
        JsonElement parsedElement = null;
        if (StringUtils.isNotBlank(json)) {
            parsedElement = this.parser.parse(json);
        }
        return parsedElement;
    }
}
