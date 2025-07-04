package com.SaharaAmussmentPark.Config;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.SaharaAmussmentPark.Service.CustomUserService;
import com.SaharaAmussmentPark.Service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilte extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final CustomUserService userService;


	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    final String authHeader = request.getHeader("Authorization");
	    final String jwt;
	    final String username;

	    try {
	        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        jwt = authHeader.substring(7); // Extract token from "Bearer <token>"
	        
	        // Check if token is expired before extracting username
	        try {
	            username = jwtService.extractUsername(jwt);
	        } catch (ExpiredJwtException e) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
	            return;
	        }

	        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);

	            if (jwtService.isTokenValid(jwt, userDetails)) {
	                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

	                UsernamePasswordAuthenticationToken token =
	                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	                securityContext.setAuthentication(token);
	                SecurityContextHolder.setContext(securityContext);
	            }
	        }

	        filterChain.doFilter(request, response);
	        
	    } catch (ExpiredJwtException e) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
	        return;
	    } catch (Exception e) {
	        e.printStackTrace(); // Log or handle the exception
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
	        return;
	    }
	}


}
