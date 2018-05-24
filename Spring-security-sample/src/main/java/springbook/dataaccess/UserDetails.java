package springbook.dataaccess;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface UserDetails extends Serializable {
	String getUsername();
	String getPassword();
	boolean isEnabled();
	boolean isAccountNonLocked();
	boolean isAccountNonExpired();
	boolean isCredentialsNonExpired();
	Collection<? extends GrantedAuthority> getAuthorities();
}
