package com.java.projectJwt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="name is required!")
    @Size(min=3, max=30, message="name must be between 3 and 30 characters")
    private String name;
    @NotEmpty(message="street is required!")
    @Size(min=3, max=30, message="street must be between 3 and 30 characters")
    private String street;
    @NotEmpty(message="city is required!")
    @Size(min=3, max=30, message="city must be between 3 and 30 characters")
    private String city;
    @NotEmpty(message="state is required!")
    @Size(min=3, max=30, message="state must be between 3 and 30 characters")
    private String state;
    @NotEmpty(message="country is required!")
    @Size(min=3, max=30, message="country must be between 3 and 30 characters")
    private String country;
    @NotEmpty(message="pincode is required!")
    @Size(min=3, max=30, message="pincode must be between 3 and 30 characters")
    private String pincode;
    @NotEmpty(message="phone is required!")
    @Size(min=3, max=30, message="phone must be between 3 and 30 characters")
    private String phone;
    
}
