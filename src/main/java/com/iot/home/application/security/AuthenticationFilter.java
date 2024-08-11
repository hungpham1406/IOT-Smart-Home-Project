package com.iot.home.application.security;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;


import com.iot.home.application.SpringApplicationContext;
import com.iot.home.application.service.UserService;
import com.iot.home.application.shared.dto.UserDto;
import com.iot.home.application.ui.model.request.UserLoginRequestModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(),UserLoginRequestModel.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),creds.getPassword(),new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        byte[] secretKeyBytes= Base64.getEncoder().encode(SecurityConstants.getTokenSecret().getBytes());
        SecretKey secretKey= new SecretKeySpec(secretKeyBytes,SignatureAlgorithm.HS512.getJcaName());

        Instant now =Instant.now();

        String userName=((User)authResult.getPrincipal()).getUsername();

        String token= Jwts.builder()
                .setSubject(userName)
                .setExpiration(Date.from(now.plusMillis(SecurityConstants.EXPIRATION_TIME)))
                .setIssuedAt(Date.from(now)).signWith(secretKey,SignatureAlgorithm.HS512).compact();

        UserService userServiceImpl= (UserService) SpringApplicationContext.getBean("userServiceImpl");
        UserDto userDto= userServiceImpl.getUser(userName);
        if(userDto.getEmailVerificationStatus()) {
            response.addHeader("UserId",userDto.getUserId());
            response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        }else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Email verification is not enabled.");
            response.getWriter().flush();
        }

    }
}
