package com.first.simple.mobile.android.model.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "primaryKey",
    "emailId",
    "emailType",
    "primary"
    })
public class EmailAddress {
	
	@JsonProperty("primaryKey")
	private String primaryKey;
	@JsonProperty("emailId")
    private String emailId;
	@JsonProperty("isPrimary")
    private boolean isPrimary;
	@JsonProperty("emailType")
    private String emailType;
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	
}
