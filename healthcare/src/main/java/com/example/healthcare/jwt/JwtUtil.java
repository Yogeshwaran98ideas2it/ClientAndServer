/**
 * 
 */
package com.example.healthcare.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.healthcare.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * The Class JwtUtil.
 *
 * @author Yogesh
 */

@Service
public class JwtUtil {
	


    /** The secret. */
	@Value("${data}")
    private String secret;
	
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

    /**
     * Extract username.
     *
     * @param token the token
     * @return the string
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract expiration.
     *
     * @param token the token
     * @return the date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract claim.
     *
     * @param <T> the generic type
     * @param token the token
     * @param claimsResolver the claims resolver
     * @return the t
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * Extract all claims.
     *
     * @param token the token
     * @return the claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if is token expired.
     *
     * @param token the token
     * @return the boolean
     */
    private Boolean isTokenExpired(String token) {
    	final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
    }

    /**
     * Generate token.
     *
     * @param username the username
     * @return the string
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    /**
     * Creates the token.
     *
     * @param claims the claims
     * @param user the subject
     * @return the string
     */
    private String createToken(Map<String, Object> claims, String userName) {

    	System.out.println("Inside createToken");
        return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * Validate token.
     *
     * @param token the token
     * @param userDetails the user details
     * @return the boolean
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }
	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}
}
