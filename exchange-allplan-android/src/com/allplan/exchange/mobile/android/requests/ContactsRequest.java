package com.allplan.exchange.mobile.android.requests;


import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.allplan.exchange.mobile.android.activities.R;
import com.allplan.exchange.mobile.android.model.contact.ContactList;
import com.allplan.exchange.mobile.android.util.Constants;

public class ContactsRequest extends SpringAndroidSpiceRequest<ContactList>{

	private long orgId;
	private String projectId;
	private String userEmail;
	private String pwd;
	
	public ContactsRequest(String userEmail, String pwd, long orgId,
			String projectId) {
		super(ContactList.class);
		this.userEmail = userEmail;
		this.pwd = pwd;
		this.orgId = orgId;
		this.projectId = projectId;
	}
	
	@Override
	public ContactList loadDataFromNetwork() throws Exception {
		String url = "";
		if (null != projectId && "" != projectId) {
			url = Constants.serverAddres + "contacts?orgId=" + orgId
					+ "&projectId=" + projectId;
		} else {
			url = Constants.serverAddres + "contacts?orgId=" + orgId;
		}
		
		HttpAuthentication httpAuthentication = new HttpBasicAuthentication(
				userEmail, pwd);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAuthorization(httpAuthentication);

		HttpEntity<?> requestEntity = new HttpEntity(httpHeaders);

		HttpEntity<ContactList> response = getRestTemplate().exchange(url,
				HttpMethod.GET, requestEntity, ContactList.class);
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
		return "contacts." +orgId+ projectId+userEmail;
	}
}
