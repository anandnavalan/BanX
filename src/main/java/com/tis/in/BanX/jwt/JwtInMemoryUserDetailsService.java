package com.tis.in.BanX.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.Role;
import com.tis.in.BanX.domain.Users;
import com.tis.in.BanX.repository.UsersRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(1L, "banxadmin",
				"$2a$10$xB8l0JOO.YT8aEt2gGBvP.tagSTmf7eXyYK.D9LCDHJHdQ7o9P2BO", "ROLE_ADMIN"));
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//				.filter(user -> user.getUsername().equals(username)).findFirst();
//
//		if (!findFirst.isPresent()) {
//			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//		}
//
//		return findFirst.get();
//	}
//	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Users> optionalUser = usersRepository.findByUsername(userName);
		if (optionalUser.isPresent()) {
			Users users = optionalUser.get();

			List<String> roleList = new ArrayList<String>();
			for (Role role : users.getRoles()) {
				roleList.add(role.getRoleName());
			}

			List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
			listAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//			return new JwtUserDetails(users.getUsername(), bCryptPasswordEncoder.encode(users.getPassword()), true, listAuthorities); // NOTE here

			return User.builder().username(users.getUsername())
					// change here to store encoded password in db
					.password(bCryptPasswordEncoder.encode(users.getPassword())).disabled(users.isDisabled())
					.accountExpired(users.isAccountExpired()).accountLocked(users.isAccountLocked())
					.credentialsExpired(users.isCredentialsExpired()).roles(roleList.toArray(new String[0])).build();
		} else {
			throw new UsernameNotFoundException("User Name is not Found");
		}
	}

}
