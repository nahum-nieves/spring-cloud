package com.yp.challenge.configuration.security;

import com.yp.challenge.model.User;
import com.yp.challenge.utilities.ConfigurationException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtTranslator {



    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
    private final String jwtIssuer = "example.io";
    @Value("security.private-key")
    private String filename;
    private RSAPrivateKey privateKey;


    public String generateAccessToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 45 * 60 * 1000)) //  45 minutes
                .signWith(SignatureAlgorithm.RS256,getPrivateKey())
                .compact();
    }

    private RSAPrivateKey getPrivateKey()  {
        if (this.privateKey == null) {
            try {
                byte[] keyBytes = FileUtils.readFileToByteArray(new File(this.filename));
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);
            }catch (Exception ex){
                throw new ConfigurationException("Invalid private key to sign JWTs");
            }
        }
        return privateKey;
    }


    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}