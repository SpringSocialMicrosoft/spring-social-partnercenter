package org.springframework.social.partnercenter.serialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonSerializationSettings {
	private Map<DeserializationFeature, Boolean> deserializationFeatures;
	private Map<SerializationFeature, Boolean> serializationFeatures;
	private Map<MapperFeature, Boolean> mapperFeatures;
	private Collection<Module> modules;

	private JsonSerializationSettings(Map<DeserializationFeature,
			Boolean> deserializationFeatures,
									  Map<SerializationFeature, Boolean> serializationFeatures,
									  Map<MapperFeature, Boolean> mapperFeatures,
									  Collection<Module> modules) {
		this.deserializationFeatures = deserializationFeatures;
		this.serializationFeatures = serializationFeatures;
		this.mapperFeatures = mapperFeatures;
		this.modules = modules;
	}

	public ObjectMapper createMapper(){
		final ObjectMapper mapper = new ObjectMapper();
		deserializationFeatures.keySet().forEach(key -> mapper.configure(key, deserializationFeatures.get(key)));
		serializationFeatures.keySet().forEach(key -> mapper.configure(key, serializationFeatures.get(key)));
		mapperFeatures.keySet().forEach(key -> mapper.configure(key, mapperFeatures.get(key)));
		modules.forEach(mapper::registerModule);
		return mapper;
	}

	public Map<DeserializationFeature, Boolean> getDeserializationFeatures() {
		return deserializationFeatures;
	}

	public Map<SerializationFeature, Boolean> getSerializationFeatures() {
		return serializationFeatures;
	}

	public Map<MapperFeature, Boolean> getMapperFeatures() {
		return mapperFeatures;
	}

	public static Builder builder(){
		return new Builder();
	}

	public static class Builder{
		private Map<DeserializationFeature, Boolean> deserializationFeatures;
		private Map<SerializationFeature, Boolean> serializationFeatures;
		private Map<MapperFeature, Boolean> mapperFeatures;
		private Collection<Module> modules;

		public Builder() {
			deserializationFeatures = new HashMap<>();
			serializationFeatures = new HashMap<>();
			mapperFeatures = new HashMap<>();
			modules = new ArrayList<>();
		}

		public Builder with(DeserializationFeature deserializationFeature, boolean activated){
			deserializationFeatures.put(deserializationFeature, activated);
			return this;
		}

		public Builder with(SerializationFeature serializationFeature, boolean activated){
			serializationFeatures.put(serializationFeature, activated);
			return this;
		}

		public Builder with(MapperFeature mapperFeature, boolean activated){
			mapperFeatures.put(mapperFeature, activated);
			return this;
		}

		public Builder with(Module module){
			this.modules.add(module);
			return this;
		}

		public JsonSerializationSettings build(){
			return new JsonSerializationSettings(deserializationFeatures, serializationFeatures, mapperFeatures, modules);
		}
	}
}
