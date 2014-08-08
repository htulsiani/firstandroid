package com.first.simple.mobile.android.model.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "primaryKey",
    "siteurl",
    "isPrimary",
    "websiteType"
    })
public class Website {
	@JsonProperty("primaryKey")
	private String primaryKey;
	@JsonProperty("siteurl")
    private String siteurl;
    @JsonProperty("isPrimary")
    private boolean isPrimary;
    @JsonProperty("websiteType")
    private String websiteType;
    
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getSiteurl() {
		return siteurl;
	}
	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getWebsiteType() {
		return websiteType;
	}
	public void setWebsiteType(String websiteType) {
		this.websiteType = websiteType;
	}
	
}
