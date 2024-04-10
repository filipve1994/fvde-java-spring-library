package io.filipvde.customspringbootstarter.security.jwt.model.dto;

import io.filipvde.customspringbootstarter.security.model.UserPrincipal;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * DTO to be used for datatransfer in the token
 * This class isolates the UserPrincipal implementation from the format of the payload in the token
 */
@Getter
public class UserTokenPayload {

    private final String name;
    private final String country;
    private final String applicationRole;
    private final String emailAddress;

    private final List<String> capabilities;

    @Builder
    public UserTokenPayload(String name, String country, String applicationRole, String emailAddress, List<String> capabilities) {
        this.name = name;
        this.country = country;
        this.applicationRole = applicationRole;
        this.emailAddress = emailAddress;
        this.capabilities = capabilities;
    }




    public static UserTokenPayload fromUserPrincipal(UserPrincipal up) {
        return UserTokenPayload.builder()
                .name(up.getName())
                .country(up.getCountry())
                .capabilities(up.getCapabilities())
                .build();
    }

//    public static UserPrincipal toUserPrincipal(UserTokenPayload utp) {
//        return UserPrincipal.us
//    }
}
