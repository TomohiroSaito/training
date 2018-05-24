package springbook.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AccountUserDetails extends User {
	
	private final Account account;
	
	public AccountUserDetails(Account account, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<GrantedAuthority> authorities) {
		super(account.getUsername(), account.getPassword(),
				account.isEnabled(), true, true, true, authorities);
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}
}
