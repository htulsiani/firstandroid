package com.first.simple.mobile.android.model.project;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "project"
})

public class ProjectsResponse {
	
	 	@JsonProperty("project")
	    
	 	private Project project;
	    
	 	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	    @JsonProperty("project")
	    public Project getProject() {
	        return project;
	    }

	    @JsonProperty("project")
	    public void setProject(Project project) {
	        this.project = project;
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




