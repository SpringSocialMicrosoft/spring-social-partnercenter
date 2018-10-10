package org.springframework.social.partnercenter.api.uri;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

public class SecurityRegion {
    public static SecurityRegion USA = new SecurityRegion("USA", "https://login.microsoftonline.com", "https://graph.windows.net", "https://api.partnercenter.microsoft.com");
    public static SecurityRegion DEU = new SecurityRegion("DEU", "https://login.microsoftonline.de", "https://graph.cloudapi.de", "https://api.partnercenter.microsoft.com");

    private String name;
    private String authority;
    private String resourceUrl;
    private String partnerServiceApiRoot;

    public SecurityRegion(String name, String authority, String resourceUrl, String partnerServiceApiRoot) {
        this.name = name;
        this.authority = authority;
        this.resourceUrl = resourceUrl;
        this.partnerServiceApiRoot = partnerServiceApiRoot;
    }

    public String getAuthority() {
        return authority;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getPartnerServiceApiRoot() {
        return partnerServiceApiRoot;
    }

    public String name() {
        return name;
    }

    public static Optional<SecurityRegion> forAuthority(String authority) {
        return values().stream()
                .filter(region -> authority.equalsIgnoreCase(region.getAuthority()))
                .findFirst();
    }

    private static List<SecurityRegion> values() {
        return ImmutableList.of(USA, DEU);
    }

    public static Optional<SecurityRegion> fromString(String name) {
        return values().stream()
                .filter(region -> region.name().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityRegion that = (SecurityRegion) o;
        return name.equals(that.name) &&
                authority.equals(that.authority) &&
                resourceUrl.equals(that.resourceUrl) &&
                partnerServiceApiRoot.equals(that.partnerServiceApiRoot);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, authority, resourceUrl, partnerServiceApiRoot);
    }
}