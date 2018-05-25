package security.core;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import security.domain.Account;

public class AccountUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private final Account account;
	private final Collection<GrantedAuthority> authorities;
	
	public AccountUserDetails(
			Account account, Collection<GrantedAuthority> authorities) {
		this.account = account;
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return account.getPassword();
	}

	public String getUsername() {
		return account.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return account.isEnabled();
	}

}
