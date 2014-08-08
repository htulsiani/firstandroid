
package com.allplan.exchange.mobile.android.model.contact;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "projAssocId",
    "projectGuid",
    "role"
})
public class Project {

    @JsonProperty("projAssocId")
    private Integer projAssocId;
    @JsonProperty("projectGuid")
    private String projectGuid;
    @JsonProperty("role")
    private String role;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("projAssocId")
    public Integer getProjAssocId() {
        return projAssocId;
    }

    @JsonProperty("projAssocId")
    public void setProjAssocId(Integer projAssocId) {
        this.projAssocId = projAssocId;
    }

    @JsonProperty("projectGuid")
    public String getProjectGuid() {
        return projectGuid;
    }

    @JsonProperty("projectGuid")
    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
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
