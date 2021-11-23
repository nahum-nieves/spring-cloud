package com.yp.security.configuration.security;

import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;


@Getter
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private String password;
    private String username;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        password = request.getParameter("password");
        username = request.getParameter("username");
    }



}