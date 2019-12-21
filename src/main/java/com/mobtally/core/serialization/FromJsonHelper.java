package com.mobtally.core.serialization;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mobtally.core.exception.InvalidJsonException;
import com.mobtally.core.exception.UnsupportedParameterException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.MonthDay;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    public Map<String, Boolean> extractMap(final Type typeOfMap, final String json) {
        return this.gsonConverter.fromJson(json, typeOfMap);
    }

    public Map<String, String> extractDataMap(final Type typeOfMap, final String json) {
        return this.gsonConverter.fromJson(json, typeOfMap);
    }

    public Map<String, Object> extractObjectMap(final Type typeOfMap, final String json) {
        return this.gsonConverter.fromJson(json, typeOfMap);
    }

    public <T> T fromJson(final String json, final Class<T> classOfT) {
        return this.gsonConverter.fromJson(json, classOfT);
    }

    public String toJson(final JsonElement jsonElement) {
        return this.gsonConverter.toJson(jsonElement);
    }

    public String toJson(final Object object) {
        return this.gsonConverter.toJson(object);
    }

    public void checkForUnsupportedParameters(final Type typeOfMap, final String json, final Set<String> supportedParams) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Map<String, Object> requestMap = this.gsonConverter.fromJson(json, typeOfMap);

        final List<String> unsupportedParameterList = new ArrayList<>();
        for (final String providedParameter : requestMap.keySet()) {
            if (!supportedParams.contains(providedParameter)) {
                unsupportedParameterList.add(providedParameter);
            }
        }

        if (!unsupportedParameterList.isEmpty()) { throw new UnsupportedParameterException(unsupportedParameterList); }
    }

    public void checkForUnsupportedParameters(final JsonObject object, final Set<String> supportedParams) {
        if (object == null) { throw new InvalidParameterException(); }

        final Set<Entry<String, JsonElement>> entries = object.entrySet();
        final List<String> unsupportedParameterList = new ArrayList<>();

        for (final Entry<String, JsonElement> providedParameter : entries) {
            if (!supportedParams.contains(providedParameter.getKey())) {
                unsupportedParameterList.add(providedParameter.getKey());
            }
        }

        if (!unsupportedParameterList.isEmpty()) { throw new UnsupportedParameterException(unsupportedParameterList); }
    }

    /**
     * @param parentPropertyName
     *            The full json path to this property,the value is appended to
     *            the parameter name while generating an error message <br>
     *            Ex: property "name" in Object "person" would be named as
     *            "person.name"
     * @param object
     * @param supportedParams
     */
    public void checkForUnsupportedNestedParameters(final String parentPropertyName, final JsonObject object,
                                                    final Set<String> supportedParams) {
        try {
            checkForUnsupportedParameters(object, supportedParams);
        } catch (UnsupportedParameterException exception) {
            List<String> unsupportedParameters = exception.getUnsupportedParameters();
            List<String> updatedUnsupportedParameters = new ArrayList<>();
            for (String unsupportedParameter : unsupportedParameters) {
                String updatedUnsupportedParameter = parentPropertyName + "." + unsupportedParameter;
                updatedUnsupportedParameters.add(updatedUnsupportedParameter);
            }
            throw new UnsupportedParameterException(updatedUnsupportedParameters);
        }

    }

    public JsonElement parse(final String json) {

        JsonElement parsedElement = null;
        if (StringUtils.isNotBlank(json)) {
            parsedElement = this.parser.parse(json);
        }
        return parsedElement;
    }

    public String extractStringNamed(final String parameterName, final JsonElement element) {
        return this.helperDelegator.extractStringNamed(parameterName, element, new HashSet<String>());
    }

    public Gson getGsonConverter() {
        return this.gsonConverter;
    }
}
