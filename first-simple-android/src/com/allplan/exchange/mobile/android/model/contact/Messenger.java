package com.allplan.exchange.mobile.android.model.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "primaryKey",
    "siteurl",
    "isPrimary",
    "websiteType"
    })
public class Messenger {
	@JsonProperty("primaryKey")
	private String primaryKey;
	@JsonProperty("messengerType")
    private String messengerType;
	@JsonProperty("messengerValue")
    private String messengerValue;
    
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getMessengerType() {
		return messengerType;
	}
	public void setMessengerType(String messengerType) {
		this.messengerType = messengerType;
	}
	public String getMessengerValue() {
		return messengerValue;
	}
	public void setMessengerValue(String messengerValue) {
		this.messengerValue = messengerValue;
	}

}
