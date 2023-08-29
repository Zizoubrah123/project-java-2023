package com.java.projectJwt.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.projectJwt.dto.JwtAuthenticationResponse;
import com.java.projectJwt.dto.SignInRequest;
import com.java.projectJwt.dto.SignUpRequest;
import com.java.projectJwt.services.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@Valid @RequestBody SignUpRequest request,BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return JwtAuthenticationResponse.builder().token(result.getAllErrors().toString()).build();
		}
    	return authenticationService.signup(request,result);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}