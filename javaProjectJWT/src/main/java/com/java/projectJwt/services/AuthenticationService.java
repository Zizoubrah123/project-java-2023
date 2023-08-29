package com.java.projectJwt.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.java.projectJwt.dto.JwtAuthenticationResponse;
import com.java.projectJwt.dto.SignInRequest;
import com.java.projectJwt.dto.SignUpRequest;
import com.java.projectJwt.models.Role;
import com.java.projectJwt.models.User;
import com.java.projectJwt.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public JwtAuthenticationResponse signup(SignUpRequest request,BindingResult result) {
	  Optional<User> potentialUser = userRepository.findByEmail(request.getEmail());
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "registerError", "Email is Taken");
		}
		// Does the entered password match the confirmation password?
		if(!request.getPassword().equals(request.getConfirm())) {
			result.rejectValue("password", "registerError", "passwords does not match");
		}
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return JwtAuthenticationResponse.builder().token(result.getAllErrors().toString()).build();
		}else {

		
      var user = User
                  .builder()
                  .firstName(request.getFirstName())
                  .lastName(request.getLastName())
                  .email(request.getEmail())
                  .password(passwordEncoder.encode(request.getPassword()))
                  .phNum(request.getPhNum())
                  .role(Role.ROLE_USER)
                  .build();

      user = userService.save(user);
      var jwt = jwtService.generateToken(user);
      return JwtAuthenticationResponse.builder().token(jwt).build();}
  }


  public JwtAuthenticationResponse signin(SignInRequest request) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      var user = userRepository.findByEmail(request.getEmail())
              .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
      var jwt = jwtService.generateToken(user);
      return JwtAuthenticationResponse.builder().token(jwt).build();
  }
  
}
