package org.springframework.social.partnercenter.api.support.servicerequest;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class ServiceRequestNote {
	private String createdByName;
	private ZonedDateTime createdDate;
	private String text;

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String createdByName;
		private String text;

		public Builder createdByName(String createdByName) {
			this.createdByName = createdByName;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public ServiceRequestNote build() {
			ServiceRequestNote note = new ServiceRequestNote();
			note.setCreatedByName(createdByName);
			note.setText(text);
			return note;
		}
	}
}
