package com.allplan.exchange.mobile.android.model.organization;
import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
"organizations",
"mayUploadLimit",
"authenticated"
})

public class AuthDTO implements Serializable{
		
		@JsonProperty("organizations")
    	private List<Organization> organizations;
		@JsonProperty("mayUploadLimit")
    	private long mayUploadLimit;
		@JsonProperty("authenticated")
    	private boolean authenticated;
    	
//    	public AuthDTO(List<Organization> organizations, long mayUploadLimit,
//    			boolean authenticated) {
//			super();
//			this.organizations = organizations;
//			this.mayUploadLimit = mayUploadLimit;
//			this.authenticated = authenticated;
//		}
		public List<Organization> getOrganizations() {
			return organizations;
		}
		public void setOrganizations(List<Organization> organizations) {
			this.organizations = organizations;
		}
		public long getMayUploadLimit() {
			return mayUploadLimit;
		}
		public void setMayUploadLimit(long mayUploadLimit) {
			this.mayUploadLimit = mayUploadLimit;
		}
		public boolean getAuthenticated() {
			return authenticated;
		}
		public void setAuthenticated(boolean authenticated) {
			this.authenticated = authenticated;
		}
		
		
    }