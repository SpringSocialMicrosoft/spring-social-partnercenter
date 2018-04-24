package org.springframework.social.partnercenter.api.relationships;

import java.util.Map;

import org.springframework.social.partnercenter.api.ResourceBase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerRelationship extends ResourceBase {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("relationshipType")
    private PartnerRelationshipType relationshipType;
    @JsonProperty("state")
    private String state;
    @JsonProperty("mpnId")
    private String mpnId;
    @JsonProperty("location")
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartnerRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(PartnerRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMpnId() {
        return mpnId;
    }

    public void setMpnId(String mpnId) {
        this.mpnId = mpnId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
