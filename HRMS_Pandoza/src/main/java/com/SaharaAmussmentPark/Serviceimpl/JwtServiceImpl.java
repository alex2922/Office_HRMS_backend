package com.SaharaAmussmentPark.Serviceimpl;



import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Service.JwtService;
import com.SaharaAmussmentPark.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;






@Service
public class JwtServiceImpl implements JwtService {
	
	 String secretKey = "NzJrYW1zUjIzMGJkdERoOG5lYjNsQzRjOWpUeTdCcjY=";
	 String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	
	@Override
	public String genrateToken(User userdetails) {
		
//		  Map<String, Object> claims = new HashMap<>();
//		    claims.put("id", userdetails.getId());
//		    claims.put("name", userdetails.getFName());
//		    claims.put("email", userdetails.getEmail());
//		    claims.put("roles", userdetails.getRole());
//		    claims.put("contactNumber", userdetails.getContactNumber());
		    
		return Jwts.builder().setSubject(userdetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 60 * 24))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
	}

	
	private <T> T extractClaims(String token,Function<Claims,T> claimsResolver) {
		final Claims claim=extractAllClaims(token);
		return claimsResolver.apply(claim);
	}
	
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	
	public String extractUsername(String token) {
		return extractClaims(token,Claims::getSubject);
	}

	private Key getSignKey() {
		byte[] key=Decoders.BASE64.decode(encodedKey);
		return Keys.hmacShaKeyFor(key);
	}
	
	public boolean isTokenValid(String token,UserDetails userdetails) {
		final String username=extractUsername(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}


	private boolean isTokenExpired(String token) {
		return extractClaims(token,Claims::getExpiration).before(new Date());
	}
	
	

}
