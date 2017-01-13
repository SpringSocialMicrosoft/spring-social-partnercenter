package org.springframework.social.partnercenter.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
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
			return reader.readValue(jsonString);

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
}
