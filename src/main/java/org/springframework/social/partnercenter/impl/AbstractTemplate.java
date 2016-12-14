package org.springframework.social.partnercenter.impl;

import org.springframework.social.MissingAuthorizationException;

public abstract class AbstractTemplate {
	private boolean isAuthorized;

	protected AbstractTemplate(boolean isAuthorized){
		this.isAuthorized = isAuthorized;
		checkAuthorization();
	}
	public void checkAuthorization(){
		if (!isAuthorized) {
			throw new MissingAuthorizationException(getProviderId());
		}
	}

	protected abstract String getProviderId();
}

