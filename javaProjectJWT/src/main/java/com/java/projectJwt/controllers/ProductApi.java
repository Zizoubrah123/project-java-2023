package com.java.projectJwt.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.projectJwt.models.Product;
import com.java.projectJwt.services.ProductService;

import jakarta.validation.Valid;



@RequestMapping("/api/v1")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductApi {
	private final ProductService productservice;
	
	public ProductApi(ProductService productservice) {
		this.productservice=productservice;
	}
	@GetMapping("/products")
	public ResponseEntity<Object>  index() {
		System.out.println(productservice.allProduct().get(0).getUser().getEmail());
		return ResponseEntity.ok().body(productservice.allProduct());
	}
	  


	@PostMapping("/products")
	public ResponseEntity<Object> createProductWithImage(@Valid @RequestBody Product product, BindingResult result
	        ) {
	    if (result.hasErrors()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
	    }
		Product savedProduct = productservice.create(product); 
		return ResponseEntity.ok("Product with ID: " + savedProduct.getId() + " created successfully.");
	}

	@GetMapping("/products/{id}")
	public Product show(@PathVariable Long id) {
		return productservice.findOne(id).orElseThrow(RuntimeException::new);
		}
	@PutMapping("/products/{id}")
	public  ResponseEntity<Object> update(@Valid @RequestBody Product product, BindingResult result,@PathVariable("id") Long id ) {
		if(result.hasErrors()) {
    		return ResponseEntity.status(400).body(result.getAllErrors());
    	}
		Product productUpdate = productservice.update(product);
	        return ResponseEntity.ok().body(productUpdate);
		}
	
	// DELETE
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		productservice.delete(id);
		return ResponseEntity.ok().build();
	}
}
