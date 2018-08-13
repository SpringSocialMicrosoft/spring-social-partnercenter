package org.springframework.social.partnercenter.api.uri;

import java.util.Optional;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class SecurityRegionTest {

	@Test
	public void testForAuthority() {
		final Optional<SecurityRegion> usSecurityRegion = SecurityRegion.forAuthority("https://login.microsoftonline.com");
		final Optional<SecurityRegion> deSecurityRegion = SecurityRegion.forAuthority("https://login.microsoftonline.de");
		final Optional<SecurityRegion> noSecurityRegion = SecurityRegion.forAuthority("https://login.microsoftonline.uk");

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(usSecurityRegion.isPresent()).isTrue();
			softly.assertThat(usSecurityRegion.get()).isEqualByComparingTo(SecurityRegion.USA);
			softly.assertThat(deSecurityRegion.isPresent()).isTrue();
			softly.assertThat(deSecurityRegion.get()).isEqualByComparingTo(SecurityRegion.DEU);
			softly.assertThat(noSecurityRegion.isPresent()).isFalse();
		});
	}

}