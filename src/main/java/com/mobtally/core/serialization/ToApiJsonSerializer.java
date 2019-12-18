package com.mobtally.core.serialization;

public interface ToApiJsonSerializer<T> {

    String serializeResult(Object object);

    String serialize(Object object);
}
