package com.yp.challenge.controller;

import com.yp.challenge.dto.LoginRequest;
import com.yp.challenge.dto.UserDto;
import com.yp.challenge.model.User;
import com.yp.challenge.configuration.security.JwtTranslator;
import com.yp.challenge.dto.mapper.UserMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Authentication")
@RestController
@RequiredArgsConstructor
public class AuthenticationApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTranslator jwtTranslator;
    private final UserMapper userMapper;

    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginRequest request) {
        try {
            final Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = (User) authenticate.getPrincipal();
            return ResponseEntity.ok()
                    .header( HttpHeaders.AUTHORIZATION, jwtTranslator.generateAccessToken(user))
                    .body(userMapper.toUserView(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
