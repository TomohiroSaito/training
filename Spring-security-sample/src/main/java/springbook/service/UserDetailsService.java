package springbook.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import springbook.dataaccess.UserDetails;

public interface UserDetailsService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
