package org.springframework.social.partnercenter.http;

import org.springframework.http.HttpHeaders;

public class PartnerCenterHttpHeaders extends HttpHeaders{
	public static final String MS_REQUEST_ID = "MS-RequestId";
	public static final String MS_CORRELATION_ID = "MS-CorrelationId";
	public static final String MS_CONTINUATION_TOKEN = "MS-ContinuationToken";

	public void setMsRequestId(String msRequestId){
		set(MS_REQUEST_ID, msRequestId);
	}

	public void setMsCorrelationId(String msCorrelationId){
		set(MS_CORRELATION_ID, msCorrelationId);
	}

	public void setMsContinuationToken(String msContinuationToken){
		set(MS_CONTINUATION_TOKEN, msContinuationToken);
	}
}
