package org.springframework.social.partnercenter.http.client;

import org.springframework.core.ParameterizedTypeReference;

public class ResultType {
	public static <E> ParameterizedTypeReference<E> typeReference(){
		return new ParameterizedTypeReference<E>() {};
	}
}
