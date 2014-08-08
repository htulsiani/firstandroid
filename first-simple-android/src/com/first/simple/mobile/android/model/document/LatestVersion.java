
package com.first.simple.mobile.android.model.document;

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
    "index",
    "uploadedBy",
    "uploadedAt",
    "files",
    "indexMetadata"
})
public class LatestVersion {

    @JsonProperty("index")
    private String index;
    @JsonProperty("uploadedBy")
    private String uploadedBy;
    @JsonProperty("uploadedAt")
    private Double uploadedAt;
    @JsonProperty("files")
    private List<File> files = new ArrayList<File>();
    @JsonProperty("indexMetadata")
    private List<Metadatum> indexMetadata = new ArrayList<Metadatum>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("index")
    public String getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(String index) {
        this.index = index;
    }

    @JsonProperty("uploadedBy")
    public String getUploadedBy() {
        return uploadedBy;
    }

    @JsonProperty("uploadedBy")
    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @JsonProperty("uploadedAt")
    public Double getUploadedAt() {
        return uploadedAt;
    }

    @JsonProperty("uploadedAt")
    public void setUploadedAt(Double uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @JsonProperty("files")
    public List<File> getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(List<File> files) {
        this.files = files;
    }

    @JsonProperty("indexMetadata")
    public List<Metadatum> getIndexMetadata() {
        return indexMetadata;
    }

    @JsonProperty("indexMetadata")
    public void setIndexMetadata(List<Metadatum> indexMetadata) {
        this.indexMetadata = indexMetadata;
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
