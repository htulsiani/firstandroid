
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
    "companyName",
    "birthDate",
    "jobTitle",
    "firstName",
    "lastName",
    "gender",
    "category",
    "note",
    "rating",
    "prefFormats",
    "title",
    "department",
    "imageId",
    "imageWidth",
    "imageHeight"
})
public class Details {

    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("birthDate")
    private Double birthDate;
    @JsonProperty("jobTitle")
    private String jobTitle;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("category")
    
    private Category category;
    @JsonProperty("note")
    private String note;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("prefFormats")
    private List<Object> prefFormats = new ArrayList<Object>();
    @JsonProperty("title")
    private String title;
    @JsonProperty("department")
    private String department;
    @JsonProperty("imageId")
    private Long imageId;
	@JsonProperty("imageWidth")
    private Integer imageWidth;
    @JsonProperty("imageHeight")
    private Integer imageHeight;
       
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("birthDate")
    public Double getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birthDate")
    public void setBirthDate(Double birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("jobTitle")
    public String getJobTitle() {
        return jobTitle;
    }

    @JsonProperty("jobTitle")
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    @JsonProperty("note")
    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty("rating")
    public String getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(String rating) {
        this.rating = rating;
    }

    @JsonProperty("prefFormats")
    public List<Object> getPrefFormats() {
        return prefFormats;
    }

    @JsonProperty("prefFormats")
    public void setPrefFormats(List<Object> prefFormats) {
        this.prefFormats = prefFormats;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(String department) {
        this.department = department;
    }
    
    @JsonProperty("imageId")
    public Long getImageId() {
		return imageId;
	}

    @JsonProperty("imageId")
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

    @JsonProperty("imageWidth")
	public Integer getImageWidth() {
		return imageWidth;
	}

    @JsonProperty("imageWidth")
	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

    @JsonProperty("imageHeight")
	public Integer getImageHeight() {
		return imageHeight;
	}

    @JsonProperty("imageHeight")
	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
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
