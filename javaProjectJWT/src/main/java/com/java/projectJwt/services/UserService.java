package com.java.projectJwt.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.projectJwt.models.User;
import com.java.projectJwt.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDetailsService userDetailsService() {
      return new UserDetailsService() {
          @Override
          public UserDetails loadUserByUsername(String username) {
              return userRepository.findByEmail(username)
                      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
          }
      };
  }

  public User save(User newUser) {
    
    return userRepository.save(newUser);
  }
  public Optional<User> findOne(Long id) {
  	Optional<User> maybeUser = userRepository.findById(id);
  	if(maybeUser.isPresent()) {
  		return maybeUser;
  	} else {
  		return null;
  	}
  }

}
