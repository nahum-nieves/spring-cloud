package com.yp.security.configuration.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yp.security.dto.mapper.UserMapper;
import com.yp.security.model.User;
import com.yp.security.utilities.ConfigurationException;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.yp.security.configuration.security.JwtConfig.TOKEN_PREFIX;
import static java.lang.String.format;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtSignator {

    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;

    @Value("${security.private-key-path}")
    private String filename;
    private RSAPrivateKey privateKey;


    public String generateAccessToken(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusMinutes(JwtConfig.EXPIRATION);
        Date expiration = Date.from(expiryDate.atZone(ZoneId.systemDefault()).toInstant());
        String token;
        try {
            token = Jwts.builder()
                    .setSubject(objectMapper.writeValueAsString(userMapper.toUserView(user)))
                    .setExpiration(expiration) //  45 minutes
                    .signWith(SignatureAlgorithm.RS512, getPrivateKey())
                    .compact();
        } catch (JsonProcessingException e) {
            log.error("Impossible generate the JWT", e);
            throw new InternalError(e);
        }
        return TOKEN_PREFIX.concat(token);
    }

    private RSAPrivateKey getPrivateKey() {

        if (this.privateKey == null) {
            try {
                byte[] keyBytes = FileUtils.readFileToByteArray(new File(this.filename));
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);
            } catch (Exception ex) {
                throw new ConfigurationException("Invalid private key to sign JWTs", ex);
            }
        }
        return privateKey;
    }

}