package com.aspire.webapp.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aspire.webapp.model.Rol;
import com.aspire.webapp.model.Users;
import com.aspire.webapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	public UserServiceImpl() {
		super();
	}
	
	@Override
	public Users updatePassword(Users user) {
		return userRepository.save(user);
	}
	
/*	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users userFound = userRepository.findByUserName(username);
		if(userFound == null) {
			throw new UsernameNotFoundException("username or password invalid");
		}
		//return new User(userFound.getName(), userFound.getPassword(), matchAuthorityRole());
		//return new User(user.getName(), user.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
*/
	private Collection<? extends GrantedAuthority> matchAuthorityRole(Collection<Rol> rols){
		return rols.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	
}
