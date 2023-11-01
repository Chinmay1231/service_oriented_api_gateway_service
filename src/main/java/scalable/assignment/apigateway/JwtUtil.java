/*
 * JwtUtil.java -- Defines JwtUtil class 
 * This code is implemented as part of assignment given to group #3 for  
 * course Scalable Service of MTECH Program Software Engineering
 * Assignment Group #3
 * Student Name : Yogesh Kshatriya
 * Student Id   : 2022MT93005
 * Course       : Scalable Services
 * Program      : MTECH Software Engineering
 * Student Email: 2022MT93005@wilp.bits-pilani.ac.in
 */
package scalable.assignment.apigateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

/**
 * The <code>JwtUtil</code> is bean that handles Jwt Token received in request. 
 * @author Yogesh Kshatriya
 */
public class JwtUtil {

    public static final String SECRET
        = "ScalableServicesGroup3YogeshChinmayDeepishShivangiDMartStoreCaseStudy";

    private final Key key;

    /**
     * Default constructor -- initializes Key
     */
    public JwtUtil() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extracts Claims from jwt token string
     * @param token          - Represents JWT token as String
     * @return               - Optional<Claims> which are user details in token
     */
    public Optional<Claims> getClaims(String token) {
        try {
            return Optional.of(
                Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody());
        } catch (RuntimeException re) {
            return Optional.empty();
        }
    }
    
    /**
     * Checks if token is expired.
     * @param claims         - Represents user details in JWT Token
     * @return               - True if token is expired otherwise false.
     */
    public boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());        
    }

}
