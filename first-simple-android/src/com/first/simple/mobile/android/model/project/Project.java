package com.first.simple.mobile.android.model.project;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "_folder", "orgName", "orgId", "group", "metadata",
		"guId", "liferayRefId", "parentGuId", "liferayRefType" })


//TODO to update the class as per the json structure
public class Project {
	
	@JsonProperty("_folder")
	private String _folder;
	@JsonProperty("orgName")
	private String orgName;
	@JsonProperty("orgId")
	private long orgId;
	
	@JsonProperty("group")
	private Group group;
	
	@JsonProperty("metadata")
	private String metadata;
	
	@JsonProperty("guId")
	private String guId;
	
	@JsonProperty("liferayRefId")
	private String liferayRefId;
	
	@JsonProperty("parentGuId")
	private String parentGuId;
	
	@JsonProperty("liferayRefType")
	private String liferayRefType;

	public String get_folder() {
		return _folder;
	}

	public void set_folder(String _folder) {
		this._folder = _folder;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getGuId() {
		return guId;
	}

	public void setGuId(String guId) {
		this.guId = guId;
	}

	public String getLiferayRefId() {
		return liferayRefId;
	}

	public void setLiferayRefId(String liferayRefId) {
		this.liferayRefId = liferayRefId;
	}

	public String getParentGuId() {
		return parentGuId;
	}

	public void setParentGuId(String parentGuId) {
		this.parentGuId = parentGuId;
	}

	public String getLiferayRefType() {
		return liferayRefType;
	}

	public void setLiferayRefType(String liferayRefType) {
		this.liferayRefType = liferayRefType;
	}
    

}
