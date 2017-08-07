package org.springframework.social.partnercenter.http.client;

public class TestRequest {
	private String name;

	public TestRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Builder builder(){
		return new Builder();
	}

	public static class Builder{
		private String name;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public TestRequest build(){
			return new TestRequest(name);
		}
	}
}
