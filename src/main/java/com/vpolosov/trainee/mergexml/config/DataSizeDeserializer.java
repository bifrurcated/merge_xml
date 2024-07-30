package com.vpolosov.trainee.mergexml.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.util.unit.DataSize;

/**
 * Преобразует строку в {@link DataSize} при десериализации.
 *
 * @author Maksim Litvinenko
 */
public class DataSizeDeserializer extends JsonObjectDeserializer<DataSize> {

    @Override
    protected DataSize deserializeObject(
        JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree
    ) {
        return DataSize.parse(tree.asText());
    }
}
