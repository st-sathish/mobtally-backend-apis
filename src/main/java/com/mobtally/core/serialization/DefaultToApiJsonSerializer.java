package com.mobtally.core.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultToApiJsonSerializer<T> implements ToApiJsonSerializer<T> {

    private final CommandProcessingResultJsonSerializer commandProcessingResultSerializer;

    private final ExcludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson excludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson;

    @Autowired
    public DefaultToApiJsonSerializer(final CommandProcessingResultJsonSerializer commandProcessingResultJsonSerializer,
                                      final ExcludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson excludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson) {
        this.commandProcessingResultSerializer = commandProcessingResultJsonSerializer;
        this.excludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson = excludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson;
    }

    @Override
    public String serialize(final Object object) {
        return this.commandProcessingResultSerializer.serialize(object);
    }

    @Override
    public String serializeResult(Object object) {
        return this.excludeNothingWithPrettyPrintingOffJsonSerializerGoogleGson.serialize(object);
    }
}
