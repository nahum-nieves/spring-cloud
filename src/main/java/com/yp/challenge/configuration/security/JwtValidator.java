package com.yp.challenge.configuration.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yp.challenge.dto.UserDto;
import com.yp.challenge.utilities.ConfigurationException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;


@Component
@RequiredArgsConstructor
@Log4j2
public class JwtValidator {

    private final ObjectMapper objectMapper;

    @Value("${security.public-key-path}")
    private String filename;
    private PublicKey publicKey;

    public UserDto getUserId(String token) {
        UserDto userDto;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(this.getPublicKey())
                    .parseClaimsJws(token)
                    .getBody();
            userDto = objectMapper.readValue(claims.getSubject(), UserDto.class);
        }catch (Exception e) {
            throw new MalformedJwtException("Corrupt JWT received",e);
        }
        return userDto;
    }

    public PublicKey getPublicKey() {
        if(this.publicKey == null){
            try {
                byte[] keyBytes = Files.readAllBytes(Paths.get(this.filename));
                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                this.publicKey = kf.generatePublic(spec);
            } catch (Exception ex) {
                throw new ConfigurationException("Invalid private key to sign JWTs");
            }
        }
        return this.publicKey;
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token);
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
