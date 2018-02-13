package org.springframework.social.partnercenter.test;

import static org.springframework.social.partnercenter.serialization.Json.fromJson;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;

public class Resource implements Closeable {

	private BufferedReader reader;
	private Scanner scan;

	public Resource(String filePath) throws IOException {
		URL f = Resource.class.getClassLoader().getResource(filePath);
		final InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(filePath);
		reader = new BufferedReader(
				new InputStreamReader(f.openStream()));
		scan = new Scanner(reader);
		scan.useDelimiter("\\r\\n");
	}

	public Resource(String filePath, String delimiter) throws IOException {
		URL f = Resource.class.getClassLoader().getResource(filePath);
		reader = new BufferedReader(
				new InputStreamReader(f.openStream()));
		scan = new Scanner(reader);
		scan.useDelimiter(delimiter);
	}

	public static Resource parseFile(String path) throws ResourceReadException {
		try {
			return new Resource(path);
		} catch (IOException e) {
			throw new ResourceReadException(e);
		}
	}

	public String getAsString() {
		StringBuilder content = new StringBuilder();
		while (scan.hasNext()) {
			content.append(scan.next());
		}
		return content.toString();
	}

	public <T> T getJsonAsObject(Class<T> aClass){
		return fromJson(getAsString(), aClass);
	}

	public <T> T getJsonAsObject(TypeReference<T> aClass){
		return fromJson(getAsString(), aClass);
	}

	public String getAsStringFlattenedString() {
		StringBuilder content = new StringBuilder();
		while (scan.hasNext()) {
			content.append(scan.next());
		}
		return StringUtils.delete(content.toString(), "\\n");
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}
