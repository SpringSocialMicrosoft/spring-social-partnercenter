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
		assertThat(instant.instant).isEqualByComparingTo(something.instant);
		assertThat(instant.zonedDateTime.toInstant()).as("ZonedDateTime").isEqualByComparingTo(something.zonedDateTime.toInstant());
		assertThat(instant.localDateTime).as("LocalDateTime").isEqualTo(something.localDateTime);
		assertThat(instant.offsetDateTime.toInstant()).as("OffsetDateTime").isEqualByComparingTo(something.offsetDateTime.toInstant());
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

		public Instant getInstant() {
			return instant;
		}

		public ZonedDateTime getZonedDateTime() {
			return zonedDateTime;
		}

		public LocalDateTime getLocalDateTime() {
			return localDateTime;
		}

		public OffsetDateTime getOffsetDateTime() {
			return offsetDateTime;
		}

		public void setInstant(Instant instant) {
			this.instant = instant;
		}

		public void setZonedDateTime(ZonedDateTime zonedDateTime) {
			this.zonedDateTime = zonedDateTime;
		}

		public void setLocalDateTime(LocalDateTime localDateTime) {
			this.localDateTime = localDateTime;
		}

		public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
			this.offsetDateTime = offsetDateTime;
		}
	}
}
