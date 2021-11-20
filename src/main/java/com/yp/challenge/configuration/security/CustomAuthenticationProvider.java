//package com.yp.configuration.security;
//
//import com.yp.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//@Log4j2
//@RequiredArgsConstructor
//@Component
//public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        CustomWebAuthenticationDetails request = ((CustomWebAuthenticationDetails)authentication.getDetails());
//        userRepository.findByUsername(authentication.getName()).ifPresentOrElse(user->{
//            if (StringUtils.hasText(user.getPassword())) {
//                if (!StringUtils.hasText(request.getPassword())) {
//                    throw new BadCredentialsException("You must to provide the password");
//                }
//                if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//                    throw new BadCredentialsException("Bad Credentials, verify your user and password.");
//                }
//            }
//        },()->{throw new BadCredentialsException("Bad Credentials, verify your user and password.");});
//        Authentication result = super.authenticate(authentication);
//        return new UsernamePasswordAuthenticationToken(
//                authentication.getName(), result.getCredentials(), result.getAuthorities());
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//}
