
package com.allplan.exchange.mobile.android.model.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customContactId",
    "owningOrgId",
    "liferayId",
    "contactType",
    "details",
    "projects",
    "references"
})
public class Contact {

    @JsonProperty("customContactId")
    private Integer customContactId;
    @JsonProperty("owningOrgId")
    private Integer owningOrgId;
    @JsonProperty("liferayId")
    private Integer liferayId;
    @JsonProperty("contactType")
    private String contactType;
    @JsonProperty("details")
    private Details details;
    @JsonProperty("projects")
    private List<Project> projects = new ArrayList<Project>();
    @JsonProperty("references")
    private List<Reference> references = new ArrayList<Reference>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customContactId")
    public Integer getCustomContactId() {
        return customContactId;
    }

    @JsonProperty("customContactId")
    public void setCustomContactId(Integer customContactId) {
        this.customContactId = customContactId;
    }

    @JsonProperty("owningOrgId")
    public Integer getOwningOrgId() {
        return owningOrgId;
    }

    @JsonProperty("owningOrgId")
    public void setOwningOrgId(Integer owningOrgId) {
        this.owningOrgId = owningOrgId;
    }

    @JsonProperty("liferayId")
    public Integer getLiferayId() {
        return liferayId;
    }

    @JsonProperty("liferayId")
    public void setLiferayId(Integer liferayId) {
        this.liferayId = liferayId;
    }

    @JsonProperty("contactType")
    public String getContactType() {
        return contactType;
    }

    @JsonProperty("contactType")
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    @JsonProperty("details")
    public Details getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(Details details) {
        this.details = details;
    }

    @JsonProperty("projects")
    public List<Project> getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @JsonProperty("references")
    public List<Reference> getReferences() {
        return references;
    }

    @JsonProperty("references")
    public void setReferences(List<Reference> references) {
        this.references = references;
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
