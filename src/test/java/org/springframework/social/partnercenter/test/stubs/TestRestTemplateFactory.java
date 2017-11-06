package org.springframework.social.partnercenter.test.stubs;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.partnercenter.serialization.Json;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestTemplate;

/**
 * This test template is configured to match the configurations in {@link org.springframework.social.oauth2.AbstractOAuth2ApiBinding#createRestTemplate(String, OAuth2Version, TokenStrategy)}.
 * I have also inlcuded the specializations added in the {@link org.springframework.social.partnercenter.api.PartnerCenterTemplate#configureRestTemplate(RestTemplate)} method where we
 * add the {@link org.springframework.http.client.BufferingClientHttpRequestFactory} and configure the {@link com.fasterxml.jackson.databind.ObjectMapper}.
 */
public class TestRestTemplateFactory {
	public static  RestTemplate createRestTemplate() {
		RestTemplate client;
		List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
		try {
			client = new RestTemplate(messageConverters);
		} catch (NoSuchMethodError e) {
			client = new RestTemplate();
			client.setMessageConverters(messageConverters);
		}
		final RestTemplate restTemplate = client;
		IntStream.range(0, client.getMessageConverters().size())
				.forEach(idx -> {
					if (MappingJackson2HttpMessageConverter.class.isInstance(restTemplate.getMessageConverters().get(idx))) {
						restTemplate.getMessageConverters().set(idx, new MappingJackson2HttpMessageConverter(Json.instance().getObjectMapper()));
					}
				});
		client.setRequestFactory(ClientHttpRequestFactorySelector.getRequestFactory());
		return client;
	}

	protected static List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(getFormMessageConverter());
		messageConverters.add(getJsonMessageConverter());
		messageConverters.add(getByteArrayMessageConverter());
		return messageConverters;
	}

	protected static FormHttpMessageConverter getFormMessageConverter() {
		FormHttpMessageConverter converter = new FormHttpMessageConverter();
		converter.setCharset(Charset.forName("UTF-8"));
		List<HttpMessageConverter<?>> partConverters = new ArrayList<HttpMessageConverter<?>>();
		partConverters.add(new ByteArrayHttpMessageConverter());
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		stringHttpMessageConverter.setWriteAcceptCharset(false);
		partConverters.add(stringHttpMessageConverter);
		partConverters.add(new ResourceHttpMessageConverter());
		converter.setPartConverters(partConverters);
		return converter;
	}

	protected static MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	protected static ByteArrayHttpMessageConverter getByteArrayMessageConverter() {
		ByteArrayHttpMessageConverter converter = new ByteArrayHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.IMAGE_JPEG, MediaType.IMAGE_GIF, MediaType.IMAGE_PNG));
		return converter;
	}
}
