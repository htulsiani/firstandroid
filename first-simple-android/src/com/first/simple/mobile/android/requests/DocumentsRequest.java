package com.first.simple.mobile.android.requests;


import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.first.simple.mobile.android.model.document.DocumentList;
import com.first.simple.mobile.android.util.Constants;

public class DocumentsRequest extends SpringAndroidSpiceRequest<DocumentList> {

	private long orgId;
	private String projectId;
	private String userEmail;
	private String pwd;
	private long start;
	private long end;
	

	public DocumentsRequest(String userEmail, String pwd, long orgId,
			String projectId, long start, long end) {
		super(DocumentList.class);
		this.userEmail = userEmail;
		this.pwd = pwd;
		this.orgId = orgId;
		this.projectId = projectId;
		this.start = start;
		this.end=end;
	}
	
	@Override
	public DocumentList loadDataFromNetwork() throws Exception {
		
		String url = Constants.serverAddres+"documents?orgId="
				+ orgId
				+ "&projectId="
				+ projectId
				+ "&start="
				+ start
				+ "&end=" + end;
			
		HttpAuthentication httpAuthentication = new HttpBasicAuthentication(
				userEmail, pwd);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAuthorization(httpAuthentication);

		HttpEntity<?> requestEntity = new HttpEntity(httpHeaders);

		HttpEntity<DocumentList> response = getRestTemplate().exchange(url,
				HttpMethod.GET, requestEntity, DocumentList.class);
		System.out.println(response.getBody());
		return response.getBody();
	}

	/**
	 * This method generates a unique cache key for this request. In this case
	 * our cache key depends just on the keyword.
	 * 
	 * @return
	 */
	public String createCacheKey() {
		return "documents." +orgId+ projectId+userEmail;
	}
}
