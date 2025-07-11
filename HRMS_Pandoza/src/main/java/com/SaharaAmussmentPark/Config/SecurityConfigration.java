package com.SaharaAmussmentPark.Config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.SaharaAmussmentPark.Service.CustomUserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigration {

	private final JwtFilte jwtFilter;

	private final CustomUserService userDetailsService;


	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable)
	        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
	        .authorizeHttpRequests(registry -> registry
	        .requestMatchers("AuthController/**","/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html","/","/eureka/**","/actuator/health","/uploadDoc/**").permitAll()
            .requestMatchers("/Employee/**").hasAnyAuthority("EMPLOYEE")
            .requestMatchers("/user/**").hasAnyAuthority("USER")
            .requestMatchers("/Admin/**").hasAnyAuthority("ADMIN")
	            .anyRequest().authenticated())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authenticationProvider(authenticationProvider())
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173","https://hrms.pandozasolutions.com/")); // Allow all origins
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow specific methods
	    configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
	    configuration.setAllowCredentials(false); // Credentials are not allowed with "*" origin

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all paths
	    return source;
	}


	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authonticationprovider = new DaoAuthenticationProvider();
		authonticationprovider.setUserDetailsService(userDetailsService.userDetailsService());
		authonticationprovider.setPasswordEncoder(passwordEncoder());
		return authonticationprovider;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	 

}
