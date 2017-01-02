package org.springframework.social.partnercenter.serialization;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class Json {
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	public static <T> T fromJson(String jsonString, Class<T> targetClass){
		try {
			ObjectReader reader = objectMapper.reader(targetClass);
			T value = reader.readValue(jsonString);
			return value;

		} catch (IOException e) {
			throw new JsonSerializationException(e);
		}
	}

	public static <T> String toJson(T objectToSerialize){
		try {

			return objectMapper.writer().writeValueAsString(objectToSerialize);
		} catch (JsonProcessingException e) {
			throw new JsonSerializationException(e);
		}
	}

	public static <T> String toJson(InputStream inputStream){
		try {
			T readValue = objectMapper.reader(JsonNode.class).readValue(inputStream);
			inputStream.reset();
			return objectMapper.writer().writeValueAsString(readValue);
		} catch (IOException e) {
			throw new JsonSerializationException(e);
		}
	}
}
