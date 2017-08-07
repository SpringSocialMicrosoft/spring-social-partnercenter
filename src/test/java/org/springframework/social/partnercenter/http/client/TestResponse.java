package org.springframework.social.partnercenter.http.client;

public class TestResponse {
	private String id;

	public TestResponse(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static Builder builder(){
		return new Builder();
	}

	public static class Builder{
		private String id;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public TestResponse build(){
			return new TestResponse(id);
		}
	}
}
