package com.allplan.exchange.mobile.android.model.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
"orgName",
"orgId",
"orgFreeDiskSpace",
"orgTotalDiskQuota",
"userRolesInOrg",
"validUntil"
})

public class Organization implements Serializable {
					
					@JsonProperty("orgName")
			    	private String orgName;	
			    	@JsonProperty("orgId")
					private long orgId;
			    	@JsonProperty("orgFreeDiskSpace")
			    	private long orgFreeDiskSpace;
			    	@JsonProperty("orgTotalDiskQuota")
			    	private long orgTotalDiskQuota;
			    	@JsonProperty("validUntil")
			    	private String validUntil;
			    	@JsonProperty("userRolesInOrg")
			    	private List<String> userRolesInOrg = new ArrayList<String>();
			    	
//			    	public Organization(String orgName, long orgId,
//			    			long orgFreeDiskSpace, long orgTotalDiskQuota,
//							String validUntil, List<String> userRolesInOrg) {
//						super();
//						this.orgName = orgName;
//						this.orgId = orgId;
//						this.orgFreeDiskSpace = orgFreeDiskSpace;
//						this.orgTotalDiskQuota = orgTotalDiskQuota;
//						this.validUntil = validUntil;
//						this.userRolesInOrg = userRolesInOrg;
//					}
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
					public long getOrgFreeDiskSpace() {
						return orgFreeDiskSpace;
					}
					public void setOrgFreeDiskSpace(long orgFreeDiskSpace) {
						this.orgFreeDiskSpace = orgFreeDiskSpace;
					}
					public long getOrgTotalDiskQuota() {
						return orgTotalDiskQuota;
					}
					public void setOrgTotalDiskQuota(long orgTotalDiskQuota) {
						this.orgTotalDiskQuota = orgTotalDiskQuota;
					}
					public String getValidUntil() {
						return validUntil;
					}
					public void setValidUntil(String validUntil) {
						this.validUntil = validUntil;
					}
					public List<String> getUserRolesInOrg() {
						return userRolesInOrg;
					}
					public void setUserRolesInOrg(List<String> userRolesInOrg) {
						this.userRolesInOrg = userRolesInOrg;
					}
			    
}
