package com.first.simple.mobile.android.model.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "primaryKey",
    "number",
    "isPrimary",
    "phoneType"
    })
public class Phone {
	@JsonProperty("primaryKey")
	private String primaryKey;
	@JsonProperty("number")
    private String number;
	@JsonProperty("isPrimary")
    private boolean isPrimary;
	@JsonProperty("phoneType")
    private String phoneType;
    
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

}
