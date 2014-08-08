package com.first.simple.mobile.android.requests;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.first.simple.mobile.android.model.project.ProjectList;
import com.first.simple.mobile.android.util.Constants;

public class ProjectsRequest extends SpringAndroidSpiceRequest<ProjectList> {

	private long orgId;
	private String userEmail;
	private String pwd;

	public ProjectsRequest(String userEmail, String pwd, long orgId) {
		super(ProjectList.class);
		this.userEmail = userEmail;
		this.pwd = pwd;
		this.orgId = orgId;
	}

	@Override
	public ProjectList loadDataFromNetwork() throws Exception {
		String url = Constants.serverAddres+"projects?orgId="
				+ orgId;
		
		
		HttpAuthentication httpAuthentication = new HttpBasicAuthentication(
				userEmail, pwd);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAuthorization(httpAuthentication);

		HttpEntity<?> requestEntity = new HttpEntity(httpHeaders);
		
		HttpEntity<ProjectList> response = getRestTemplate().exchange(url,
				HttpMethod.GET, requestEntity, ProjectList.class);
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
		return "projects."+ orgId + userEmail;
	}
}
