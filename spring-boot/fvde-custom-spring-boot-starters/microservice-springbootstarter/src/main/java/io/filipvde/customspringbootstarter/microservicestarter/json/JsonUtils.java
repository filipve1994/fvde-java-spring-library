package io.filipvde.customspringbootstarter.microservicestarter.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.filipvde.customspringbootstarter.microservicestarter.exceptions.JsonMappingException;
import jakarta.annotation.Nonnull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public final class JsonUtils {

	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);


    private static ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .disable(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            )
            .enable(
                    SerializationFeature.INDENT_OUTPUT
            )
            .build();

    private static ObjectWriter jsonWriter = mapper.writerWithDefaultPrettyPrinter();

    @Nonnull
    public static String toJson(@Nonnull final Object obj) {
        try {
            return jsonWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Failed to convert Object to Json: " + e.toString());
            throw new JsonMappingException(e);
        }
    }

    @Nonnull
    public static <C> C toObject(@Nonnull final String data, @Nonnull final Class<C> dataClzz) {
        try {
            return mapper.readValue(data, dataClzz);
        } catch (JsonProcessingException e) {
            log.warn("Failed to convert Json to Object: " + e.toString());
            throw new JsonMappingException(e);
        }
    }

    @Nonnull
    public ObjectMapper getConfiguredObjectMapper() {
        return mapper;
    }
}
