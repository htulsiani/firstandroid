package com.allplan.exchange.mobile.android.model.organization;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
"useremail",
"password",
"authDTO"
})
public class Follower {
	@JsonProperty("useremail")
	private String useremail;
	@JsonProperty("password")
	private String password;
	@JsonProperty("authDTO")
	private AuthDTO authDTO;
    
//	public Follower(
//			String useremail,
//			String password,
//			AuthDTO authDTO) {
//		super();
//		this.useremail = useremail;
//		this.password = password;
//		this.authDTO = authDTO;
//	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthDTO getAuthDTO() {
		return authDTO;
	}

	public void setAuthDTO(AuthDTO authDTO) {
		this.authDTO = authDTO;
	}
}




