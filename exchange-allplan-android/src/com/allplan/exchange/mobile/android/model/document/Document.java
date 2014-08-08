
package com.allplan.exchange.mobile.android.model.document;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "planName",
    "planNumber",
    "documentGuId",
    "allplanProjectName",
    "latestVersion"
})
public class Document {

	@JsonProperty("planName")
    private String planName;
    @JsonProperty("planNumber")
    private Object planNumber;
    @JsonProperty("documentGuId")
    private String documentGuId;
    @JsonProperty("allplanProjectName")
    private Object allplanProjectName;
    @JsonProperty("latestVersion")
    private LatestVersion latestVersion;
    
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("planName")
    public String getPlanName() {
        return planName;
    }

    @JsonProperty("planName")
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @JsonProperty("planNumber")
    public Object getPlanNumber() {
        return planNumber;
    }

    @JsonProperty("planNumber")
    public void setPlanNumber(Object planNumber) {
        this.planNumber = planNumber;
    }

    @JsonProperty("documentGuId")
    public String getDocumentGuId() {
        return documentGuId;
    }

    @JsonProperty("documentGuId")
    public void setDocumentGuId(String documentGuId) {
        this.documentGuId = documentGuId;
    }

    @JsonProperty("allplanProjectName")
    public Object getAllplanProjectName() {
        return allplanProjectName;
    }

    @JsonProperty("allplanProjectName")
    public void setAllplanProjectName(Object allplanProjectName) {
        this.allplanProjectName = allplanProjectName;
    }

    @JsonProperty("latestVersion")
    public LatestVersion getLatestVersion() {
        return latestVersion;
    }

    @JsonProperty("latestVersion")
    public void setLatestVersion(LatestVersion latestVersion) {
        this.latestVersion = latestVersion;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
