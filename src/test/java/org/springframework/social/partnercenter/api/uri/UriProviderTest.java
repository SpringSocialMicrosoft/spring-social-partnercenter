package org.springframework.social.partnercenter.api.uri;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class UriProviderTest {

	@Test
	public void test() {
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(UriProvider.DE.getUserPlusAppResource()).isEqualTo("https://api.partnercenter.microsoft.com");
			softly.assertThat(UriProvider.DE.getAppResource()).isEqualTo("https://graph.cloudapi.de");
			softly.assertThat(UriProvider.DE.getAuthority()).isEqualTo("https://login.microsoftonline.de");

			softly.assertThat(UriProvider.US.getUserPlusAppResource()).isEqualTo("https://api.partnercenter.microsoft.com");
			softly.assertThat(UriProvider.US.getAuthority()).isEqualTo("https://login.microsoftonline.com");
			softly.assertThat(UriProvider.US.getAppResource()).isEqualTo("https://graph.windows.net");
		});

	}
}