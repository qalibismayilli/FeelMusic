package com.company.feelmusic.service;

import com.company.feelmusic.dto.request.LoginRequest;
import com.company.feelmusic.dto.response.TokenResponse;
import com.company.feelmusic.dto.response.UserResponseDto;
import com.company.feelmusic.exception.GenericException;
import com.company.feelmusic.util.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenGenerator tokenGenerator,
                       AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponse login(LoginRequest request){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

            return new TokenResponse(tokenGenerator.generateToken(auth),
                    userService.findByUsername(request.getUsername()));
        }catch (Exception ex){
            throw new GenericException("User Not Found");
        }
    }

    public UserResponseDto getLoggedInUser(){
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
        return userService.findByUsername(username);
    }
}
