package com.yp.security.controller;

import com.yp.security.configuration.security.JwtSignator;
import com.yp.security.dto.LoginRequest;
import com.yp.security.dto.mapper.UserMapper;
import com.yp.security.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    private final JwtSignator jwtSignator;
    private final UserMapper userMapper;

    @PostMapping("login")
    @ApiOperation(value = "Authentication method",tags = "Authentication")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Login succes, your JWT is in 'Authorization' header response"),
            @ApiResponse(code = 401, message = "Bad Credentials, checkout your credentials") })
    public ResponseEntity login(@RequestBody @ApiParam(name = "Credentials",required = true) @Valid LoginRequest request) {
        try {
            final Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = (User) authenticate.getPrincipal();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .header( HttpHeaders.AUTHORIZATION, jwtSignator.generateAccessToken(user))
                    .build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
        }
    }
}
