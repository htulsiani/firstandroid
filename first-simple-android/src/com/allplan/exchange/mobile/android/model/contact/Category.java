
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
    "categoryId",
    "categoryType",
    "categoryValue"
})
public class Category {

    @JsonProperty("categoryId")
    private Integer categoryId;
    @JsonProperty("categoryType")
    private String categoryType;
    @JsonProperty("categoryValue")
    private String categoryValue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("categoryId")
    public Integer getCategoryId() {
        return categoryId;
    }

    @JsonProperty("categoryId")
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("categoryType")
    public String getCategoryType() {
        return categoryType;
    }

    @JsonProperty("categoryType")
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @JsonProperty("categoryValue")
    public String getCategoryValue() {
        return categoryValue;
    }

    @JsonProperty("categoryValue")
    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
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
