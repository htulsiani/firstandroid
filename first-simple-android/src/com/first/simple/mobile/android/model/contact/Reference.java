
package com.first.simple.mobile.android.model.contact;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "customContactRefsId",
    "contactRefType",
    "emailAddress",
    "phone",
    "address",
    "website",
    "messenger"
})
public class Reference {

    @JsonProperty("customContactRefsId")
    private Integer customContactRefsId;
    @JsonProperty("contactRefType")
    private String contactRefType;
    @JsonProperty("emailAddress")
    private EmailAddress emailAddress;
    @JsonProperty("phone")
    private Phone phone;
    @JsonProperty("address")
    
    private Address address;
    @JsonProperty("website")
    private Website website;
    @JsonProperty("messenger")
    private Messenger messenger;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customContactRefsId")
    public Integer getCustomContactRefsId() {
        return customContactRefsId;
    }

    @JsonProperty("customContactRefsId")
    public void setCustomContactRefsId(Integer customContactRefsId) {
        this.customContactRefsId = customContactRefsId;
    }

    @JsonProperty("contactRefType")
    public String getContactRefType() {
        return contactRefType;
    }

    @JsonProperty("contactRefType")
    public void setContactRefType(String contactRefType) {
        this.contactRefType = contactRefType;
    }

    @JsonProperty("emailAddress")
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty("emailAddress")
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    @JsonProperty("phone")
    public Phone getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("website")
    public Website getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(Website website) {
        this.website = website;
    }

    @JsonProperty("messenger")
    public Messenger getMessenger() {
        return messenger;
    }

    @JsonProperty("messenger")
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
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
