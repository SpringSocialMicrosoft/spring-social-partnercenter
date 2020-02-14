package org.springframework.social.partnercenter.api;

import static org.springframework.social.partnercenter.http.PartnerCenterHttpHeaders.MS_CONTINUATION_TOKEN;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.billing.usage.UtilizationRecord;
import org.springframework.social.partnercenter.http.client.RestResource;

public class PagingResourceTemplate<T> extends AbstractTemplate implements PagingResourceOperations<T>{
	private final RestResource restResource;
	private static final String SUBSCRIPTIONS = "subscriptions";
	private static final String UTILIZATIONS = "utilizations";
	private static final String AZURE = "azure";
	private ParameterizedTypeReference<PartnerCenterResponse<T>> typeReference;
	protected PagingResourceTemplate(RestResource restResource, boolean isAuthorized, ParameterizedTypeReference<PartnerCenterResponse<T>> typeReference) {
		super(isAuthorized);
		this.restResource = restResource;
		this.typeReference = typeReference;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<T>> next(String continuationToken){
		return restResource.request()
				.header(MS_CONTINUATION_TOKEN, continuationToken)
				.queryParam("seekOperation", SeekOperation.NEXT.getValue())
				.get(typeReference);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<T>> previous(String continuationToken) {
		return restResource.request()
				.header(MS_CONTINUATION_TOKEN, continuationToken)
				.queryParam("seekOperation", SeekOperation.PREVIOUS.getValue())
				.get(typeReference);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<T>> first(String continuationToken) {
		return restResource.request()
				.header(MS_CONTINUATION_TOKEN, continuationToken)
				.queryParam("seekOperation", SeekOperation.FIRST.getValue())
				.get(typeReference);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<T>> last(String continuationToken) {
		return restResource.request()
				.header(MS_CONTINUATION_TOKEN, continuationToken)
				.queryParam("seekOperation", SeekOperation.LAST.getValue())
				.get(typeReference);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<T>> page(String continuationToken, int pageIndex) {
		return restResource.request()
				.header(MS_CONTINUATION_TOKEN, continuationToken)
				.queryParam("seekOperation", SeekOperation.PAGE_INDEX.getValue())
				.queryParam("page", pageIndex)
				.get(typeReference);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> next(String continuationToken, String customerId, String subscriptionId) {
		return restResource.request()
			.header(MS_CONTINUATION_TOKEN, continuationToken)
			.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, UTILIZATIONS, AZURE)
			.queryParam("seek_operation", SeekOperation.NEXT.getValue())
			.get((new ParameterizedTypeReference<PartnerCenterResponse<UtilizationRecord>>() {}));
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
