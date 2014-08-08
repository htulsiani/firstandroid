
package com.first.simple.mobile.android.model.document;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "extension",
    "title",
    "size",
    "fileEntryId"
})
public class File {
	
    @JsonProperty("extension")
    private String extension;
    @JsonProperty("title")
    private String title;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("fileEntryId")
    private Integer fileEntryId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("extension")
    public String getExtension() {
        return extension;
    }

    @JsonProperty("extension")
    public void setExtension(String extension) {
        this.extension = extension;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("fileEntryId")
    public Integer getFileEntryId() {
        return fileEntryId;
    }

    @JsonProperty("fileEntryId")
    public void setFileEntryId(Integer fileEntryId) {
        this.fileEntryId = fileEntryId;
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
