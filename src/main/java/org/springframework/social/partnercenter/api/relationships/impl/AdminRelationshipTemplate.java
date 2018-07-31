package org.springframework.social.partnercenter.api.relationships.impl;

import org.springframework.social.partnercenter.api.relationships.AdminRelationshipOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminRelationshipTemplate extends RelationshipTemplate implements AdminRelationshipOperations {
    public AdminRelationshipTemplate(RestResource restResource, boolean isAuthorized) {
        super(restResource, isAuthorized);
    }
}
