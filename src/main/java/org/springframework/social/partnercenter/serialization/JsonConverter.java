package org.springframework.social.partnercenter.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {
	private ObjectMapper objectMapper;

	public JsonConverter(){
		objectMapper = JsonSerializationSettings.builder()
				.with(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
				.with(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false)
				.with(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.with(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
				.with(new JavaTimeModule())
				.build().createMapper();
	}
	public JsonConverter(JsonSerializationSettings serializationSettings) {
		this.objectMapper = serializationSettings.createMapper();
	}

	public <T> T fromJson(String jsonString, Class<T> targetClass){
		try {
			ObjectReader reader = objectMapper
					.reader()
					.forType(targetClass);

			return reader.readValue(jsonString);

		} catch (IOException e) {
			throw new JsonSerializationException(e);
		}
	}

	public <T> T fromJson(String jsonString, Class<T> targetClass, JsonSerializationSettings serializationSettings){
		try {
			ObjectReader reader = serializationSettings.createMapper()
					.reader()
					.forType(targetClass);

			return reader.readValue(jsonString);

		} catch (IOException e) {
			throw new JsonSerializationException(e);
		}
	}

	public <T> String toJson(T objectToSerialize){
		try {

			return objectMapper.writer().writeValueAsString(objectToSerialize);
		} catch (JsonProcessingException e) {
			throw new JsonSerializationException(e);
		}
	}

	public <T> String toJson(T objectToSerialize, JsonSerializationSettings serializationSettings){
		try {

			return serializationSettings.createMapper().writer().writeValueAsString(objectToSerialize);
		} catch (JsonProcessingException e) {
			throw new JsonSerializationException(e);
		}
	}

	public JsonNode toJsonNode(String json){
		try {
			return objectMapper.reader().readTree(json);
		} catch (IOException e) {
			throw new JsonSerializationException(e);
		}
	}

	public JsonNode toJsonNode(Object json){
		return objectMapper.valueToTree(json);
	}

	public void configure(JsonSerializationSettings serializationSettings) {
		objectMapper = serializationSettings.createMapper();
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
