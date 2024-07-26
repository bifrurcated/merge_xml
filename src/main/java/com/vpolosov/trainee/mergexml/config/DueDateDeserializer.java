package com.vpolosov.trainee.mergexml.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.time.Duration;

/**
 * Преобразует строку в {@link Duration} при десериализации.
 *
 * @author Maksim Litvinenko
 */
public class DueDateDeserializer extends JsonObjectDeserializer<Duration> {

    @Override
    protected Duration deserializeObject(
        JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree
    ) {
        return DurationStyle.detectAndParse(tree.asText());
    }
}
