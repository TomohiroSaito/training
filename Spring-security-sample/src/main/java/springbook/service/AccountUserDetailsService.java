package springbook.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import springbook.dataaccess.UserDetails;

public class AccountUserDetailsService implements UserDetailsService {
	@Autowired
	AccountRepository accountRepository;
	
	@Transactional(readOnry = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Account account = Optional<T>.ofNullable(accountRepository.findOne(username))
				.orElseThrow(() -> new UsernameNotFoundException("user not found."));
		return new AccountUserDetails(account, getAuthorities(account));
	}
	
	private Collection<GrantedAuthority> getAuthorities(Account account) {
		if (account.isAdmin()) {
			return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		} else {
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}
	}

}
