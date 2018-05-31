package org.springframework.social.partnercenter.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.social.partnercenter.http.logging.LoggingRequestInterceptor;

public class PartnerCenterTemplateTest {
	private PartnerCenterTemplate template;

	@Before
	public void init() {
		template = new PartnerCenterTemplate(UriProvider.DEFAULT_URL_PROVIDER, "", "");
	}

	@Test
	public void testEnableSlf4j() {
		template.enableSlf4j(LogLevel.INFO);
		template.enableSlf4j(LogLevel.INFO);
		template.enableSlf4j(LogLevel.INFO);
		template.enableSlf4j(LogLevel.INFO);
		assertThat(template.getRestTemplate().getInterceptors().stream().filter(LoggingRequestInterceptor.class::isInstance).count())
				.describedAs("Must only contain one logging interceptor even if multiple have been added")
				.isEqualTo(1);
	}

	@Test
	public void testIsEnableSlf4j() {
		SoftAssertions.assertSoftly(softly -> {
					softly.assertThat(template.isSlf4jEnabled()).describedAs("isSlf4jEnabled must be false if we have not enabled it yet").isFalse();
					template.enableSlf4j(LogLevel.INFO);
					softly.assertThat(template.isSlf4jEnabled()).describedAs("isSlf4jEnabled must be true if we have enabled it").isTrue();
				}
		);
	}
}