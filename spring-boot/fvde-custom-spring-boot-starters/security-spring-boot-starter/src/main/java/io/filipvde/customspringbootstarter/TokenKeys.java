package io.filipvde.customspringbootstarter;

public final class TokenKeys {

    // Remember to add to the AccessTokenBuilder is you need the property for testing
    // Otherwise the created test token will NOT contain your property

    public static final String USERNAME = "user_name";
    public static final String PREFERRED_USERNAME = "preferred_username"; //For anonymous users mapped in access token.
    public static final String ROLES = "roles"; //For list of all functional roles in realm_access claim
    public static final String AUTHORITIES = "authorities";
    public static final String REALM_ACCESS = "realm_access";
    public static final String CLIENT_ID = "client_id";
    public static final String LANGUAGE = "language";
    public static final String COUNTRY = "country";
    public static final String EXPIRY = "exp";
    public static final String GRANT_TYPE = "grant_type";
    public static final String EMAIL = "email";
    public static final String AUDIENCE = "aud";
    public static final String VENDOR_ID = "vendor_id";
    public static final String VENDOR_DOMAIN = "vendor_domain";
    public static final String CUST_ID = "cust_id";
    public static final String TOKENTYPE = "token_type";
    public static final String PARTY_ID = "party_id";
    public static final String PARTY_SYSCODE = "party_syscode";
    public static final String PARTY_IDS = "party_ids";
    public static final String CURRENT_ACCEPTED_TERM_VERSION = "current_accepted_term_version";
    public static final String CAPABILITIES = "capabilities";

    public static final String ONEPAMREFS = "onePamMandates"; //in line with OAuthToken class


    private TokenKeys() {
    }
}
