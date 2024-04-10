package io.filipvde.customspringbootstarter.security.config;

public class SecurityConstants {

    public static final String ROLE_ACTUATOR = "ACTUATOR";
    public static final String ROLE_USER = "USER";

    public static final String[] SWAGGER_URLS = new String[]{
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/**",
            "/v3/api-docs/**",
            "/actuator/**",

            "/h2-console/**",
            "/h2/**"
    };


    public static final String[] SECURITY_URLS_TO_PERMIT_ALL = new String[]{
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/**",
            "/v3/api-docs/**",
            "/actuator/**",

            "/h2-console/**",
            "/h2/**"
    };

}
