package com.mobtally.core.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExcludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson {

    private final Gson gson;

    public ExcludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson() {
        final GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
    }

    public String serialize(final Object result) {
        String returnedResult = null;
        final String serializedResult = this.gson.toJson(result);
        if (!"null".equalsIgnoreCase(serializedResult)) {
            returnedResult = serializedResult;
        }
        return returnedResult;
    }
}
