package org.springframework.social.partnercenter.api;

import org.springframework.http.ResponseEntity;

public interface PagingResourceOperations<T> {
	ResponseEntity<PartnerCenterResponse<T>> next(String continuationToken);
	ResponseEntity<PartnerCenterResponse<T>> previous(String continuationToken);
	ResponseEntity<PartnerCenterResponse<T>> first(String continuationToken);
	ResponseEntity<PartnerCenterResponse<T>> last(String continuationToken);
	ResponseEntity<PartnerCenterResponse<T>> page(String continuationToken, int pageIndex);
}
