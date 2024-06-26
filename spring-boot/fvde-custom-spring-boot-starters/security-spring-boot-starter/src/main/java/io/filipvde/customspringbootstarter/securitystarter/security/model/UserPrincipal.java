package io.filipvde.customspringbootstarter.securitystarter.security.model;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@ToString
public class UserPrincipal implements Principal {

	private static final Logger log = LoggerFactory.getLogger(UserPrincipal.class);


    private static final String ROLE_PREFIX = "ROLE_";

    private String name;

    private List<String> capabilities = Collections.emptyList();

//    @NotNull
//    private final Optional<Language> preferredLanguage;

    @NotNull
    private final String country;

	public UserPrincipal(String name, List<String> capabilities, String country) {
		this.name = name;
		this.capabilities = capabilities;
		this.country = country;
	}


//    public UserPrincipal(String name, List<String> capabilities, Optional<Language> preferredLanguage, String country) {
//        this.name = name;
//        this.capabilities = capabilities;
//        this.preferredLanguage = preferredLanguage;
//        this.country = country;
//    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }

    @Nonnull
    public Collection<GrantedAuthority> getAuthorities() {
        return capabilities.stream()
                .map(c -> ROLE_PREFIX + c)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
