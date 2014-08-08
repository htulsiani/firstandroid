
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
    "primaryKey",
    "street1",
    "street2",
    "street3",
    "city",
    "zip",
    "country",
    "addressType",
    "primary"
})
public class Address {

    @JsonProperty("primaryKey")
    private String primaryKey;
    @JsonProperty("street1")
    private String street1;
    @JsonProperty("street2")
    private String street2;
    @JsonProperty("street3")
    private String street3;
    @JsonProperty("city")
    private String city;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("country")
    private String country;
    @JsonProperty("addressType")
    private String addressType;
    @JsonProperty("primary")
    private Boolean primary;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("primaryKey")
    public String getPrimaryKey() {
        return primaryKey;
    }

    @JsonProperty("primaryKey")
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    @JsonProperty("street1")
    public String getStreet1() {
        return street1;
    }

    @JsonProperty("street1")
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    @JsonProperty("street2")
    public String getStreet2() {
        return street2;
    }

    @JsonProperty("street2")
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    @JsonProperty("street3")
    public String getStreet3() {
        return street3;
    }

    @JsonProperty("street3")
    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("addressType")
    public String getAddressType() {
        return addressType;
    }

    @JsonProperty("addressType")
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @JsonProperty("primary")
    public Boolean getPrimary() {
        return primary;
    }

    @JsonProperty("primary")
    public void setPrimary(Boolean primary) {
        this.primary = primary;
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
