package org.springframework.social.partnercenter.serialization;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import org.junit.Test;

public class JsonTest {

	@Test
	public void testTimeSerialization(){
		Something something = new Something();
		String s = Json.toJson(something);
		Something instant = Json.fromJson(s, Something.class);
		assertThat(instant.instant.getEpochSecond()).isBetween(something.instant.getEpochSecond()-2, something.instant.getEpochSecond() +2);
		assertThat(instant.instant).isEqualToIgnoringGivenFields(something.instant, "nanos", "seconds");
		assertThat(instant.zonedDateTime).as("ZonedDateTime").isEqualToIgnoringNanos(something.zonedDateTime);
		assertThat(instant.localDateTime).as("LocalDateTime").isEqualToIgnoringNanos(something.localDateTime);
		assertThat(instant.offsetDateTime).as("OffsetDateTime").isEqualToIgnoringNanos(something.offsetDateTime);
	}


	public static class Something {
		private Instant instant;
		private ZonedDateTime zonedDateTime;
		private LocalDateTime localDateTime;
		private OffsetDateTime offsetDateTime;

		public Something() {
			instant = Instant.now();
			zonedDateTime = ZonedDateTime.now();
			localDateTime = LocalDateTime.now();
			offsetDateTime = OffsetDateTime.now();
		}
	}
}
