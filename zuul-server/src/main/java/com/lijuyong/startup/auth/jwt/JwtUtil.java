package com.lijuyong.startup.auth.jwt;

import com.lijuyong.startup.auth.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by john on 2017/3/6.
 */
@Component
public class JwtUtil {

    private static final String ID_KEY_NAME ="id";
    private static final String ORG_ID_KEY_NAME ="org";
    private static final String ROLE_ID_KEY_NAME ="role";
    private static final Long NORMAL_TOKEN_TTL = 30*60*1000L; //30分钟
    private static final Long TEMP_TOKEN_TTL = 5*60*1000L;


    @Value("${jwt.secret}")
    private String secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public JwtUser parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();


            JwtUser jwtUser = new JwtUser();
            jwtUser.setName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get(ID_KEY_NAME)));
            jwtUser.setRoleId(Long.parseLong((String)body.get(ROLE_ID_KEY_NAME)));
            jwtUser.setOrgId(Long.parseLong((String)body.get(ORG_ID_KEY_NAME)));



            return jwtUser;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param jwtUser the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(JwtUser jwtUser) {

        Claims claims = Jwts.claims().setSubject(jwtUser.getName());
        claims.put(ID_KEY_NAME, jwtUser.getId() + "");
        claims.put(ROLE_ID_KEY_NAME, jwtUser.getRoleId()+"");
        claims.put(ORG_ID_KEY_NAME, jwtUser.getOrgId()+"");
        Date timestampNow = new Date();
        claims.setIssuedAt(timestampNow);

        Date expires = new Date(timestampNow.getTime() + NORMAL_TOKEN_TTL);
        claims.setExpiration(expires);


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
