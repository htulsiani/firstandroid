package com.first.simple.mobile.android.model.project;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "name", "type", "active", "groupId", "companyId",
		"friendlyURL", "liveGroupId", "classNameId", "classPK", "typeSettings","creatorUserId" })


//TODO to update the class as per the json structure
public class Group {
	@JsonProperty("name")
	private String name;
	@JsonProperty("type")
	private String type;
	@JsonProperty("active")
	private String active;
	
	@JsonProperty("groupId")
	private String groupId;
	
	@JsonProperty("companyId")
	private String companyId;
	
	@JsonProperty("friendlyURL")
	private String friendlyURL;
	
	@JsonProperty("liveGroupId")
	private String liveGroupId;
	
	@JsonProperty("classNameId")
	private String classNameId;
	
	@JsonProperty("classPK")
	private String classPK;
    
	@JsonProperty("typeSettings")
	private String typeSettings;
	
	@JsonProperty("creatorUserId")
	private String creatorUserId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getFriendlyURL() {
		return friendlyURL;
	}

	public void setFriendlyURL(String friendlyURL) {
		this.friendlyURL = friendlyURL;
	}

	public String getLiveGroupId() {
		return liveGroupId;
	}

	public void setLiveGroupId(String liveGroupId) {
		this.liveGroupId = liveGroupId;
	}

	public String getClassNameId() {
		return classNameId;
	}

	public void setClassNameId(String classNameId) {
		this.classNameId = classNameId;
	}

	public String getClassPK() {
		return classPK;
	}

	public void setClassPK(String classPK) {
		this.classPK = classPK;
	}

	public String getTypeSettings() {
		return typeSettings;
	}

	public void setTypeSettings(String typeSettings) {
		this.typeSettings = typeSettings;
	}

	public String getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}


	
}
