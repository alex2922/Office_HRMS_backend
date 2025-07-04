package com.SaharaAmussmentPark.Serviceimpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Repository.UserRepository;
import com.SaharaAmussmentPark.Service.CustomUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements CustomUserService {

	private final UserRepository repo;

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				return (UserDetails) repo.findByEmail(email)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}

		};
	}

}
