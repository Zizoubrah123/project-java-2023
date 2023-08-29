package com.java.projectJwt.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @NotEmpty(message="firstName is required!")
  @Size(min=3, max=30, message="FirstName must be between 3 and 30 characters")
  String firstName;
  @NotEmpty(message="LastName is required!")
  @Size(min=3, max=30, message="LastName must be between 3 and 30 characters")
  String lastName;
  @NotEmpty(message="Email is required!")
  @Email(message="Please enter a valid email!")
  @Column(unique = true)
  String email;
  @NotEmpty(message="Password is required!")
  @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
  String password;
  @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10)
  private Integer phNum;

  @Enumerated(EnumType.STRING)
  Role role;

  @Column(updatable=false)
  @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
  @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
  
//	----- methods ---
  // other getters and setters removed for brevity
  @PrePersist
  protected void onCreate(){
      this.createdAt = new Date();
  }
  @PreUpdate
  protected void onUpdate(){
      this.updatedAt = new Date();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
      // our "username" for security is the email field
      return email;
  }

  @Override
  public boolean isAccountNonExpired() {
      return true;
  }

  @Override
  public boolean isAccountNonLocked() {
      return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true;
  }

  @Override
  public boolean isEnabled() {
      return true;
  }

}
