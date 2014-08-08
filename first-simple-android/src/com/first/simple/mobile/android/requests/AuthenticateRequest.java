package com.first.simple.mobile.android.requests;


import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.first.simple.mobile.android.model.organization.Follower;
import com.first.simple.mobile.android.util.Constants;

public class AuthenticateRequest extends SpringAndroidSpiceRequest<Follower> {
	
	private String userEmail;
    private String pwd;

    public AuthenticateRequest(String userEmail, String pwd) {
        super(Follower.class);
        this.userEmail = userEmail;
        this.pwd = pwd;
    }

    @Override
    public Follower loadDataFromNetwork() throws Exception {
    	String url = Constants.serverAddres+"authenticate";
    	
    	HttpAuthentication httpAuthentication = new HttpBasicAuthentication(userEmail, pwd);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAuthorization(httpAuthentication);
        
        HttpEntity<?> requestEntity = new HttpEntity(httpHeaders);
        
		HttpEntity<Follower> response = getRestTemplate().exchange(url,
				HttpMethod.GET, requestEntity, Follower.class);
        
        return response.getBody();

    }

    /**
     * This method generates a unique cache key for this request. In this case
     * our cache key depends just on the keyword.
     * @return
     */
    public String createCacheKey() {
        return "organization." + userEmail;
    }
}
