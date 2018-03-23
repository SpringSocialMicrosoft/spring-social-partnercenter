package org.springframework.social.partnercenter.api;

public class ResourceBaseWithLinks<T> extends ResourceBase{
	private T links;

	public T getLinks() {
		return links;
	}

	public void setLinks(T links) {
		this.links = links;
	}
}
