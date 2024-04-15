package io.filipvde.customspringbootstarter.jpastarter.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The Class AuditorAwareImpl.
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	/**
	 * Gets the current auditor.
	 *
	 * @return the current auditor
	 */
	@Override
	public Optional<String> getCurrentAuditor(){
		String username = "FVE";
		// SecurityContextHolder.getContext().getAuthentication().getName();
		return Optional.of(username);
	}
}