package com.java.projectJwt.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.projectJwt.models.CartProduct;
import com.java.projectJwt.models.Product;
import com.java.projectJwt.models.User;
import com.java.projectJwt.models.Wishlist;
import com.java.projectJwt.services.ProductService;
import com.java.projectJwt.services.UserService;
import com.java.projectJwt.services.WishlistService;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class WishlistApi {
	@Autowired
	WishlistService wishlistService;
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/wishlist/{userId}")
	public ResponseEntity<Object> index(@PathVariable("userId") Long userId) {
		Optional<User> user=userService.findOne(userId);
		System.out.println(user.get().getWishlist());
		return ResponseEntity.ok().body(user.get().getWishlist());
	}
	@PostMapping("/wishlist/{userId}/{productId}")
	public ResponseEntity<Object> create (@PathVariable("userId") Long userId,@PathVariable("productId") Long productId){
		
		Optional<User> user=userService.findOne(userId);
		Optional<Product> product = productService.findOne(productId);
		Wishlist whishlist=new Wishlist();
		whishlist.setUser(user.get());
		whishlist.setProducts(product.get());
		Wishlist create = wishlistService.create(whishlist);
		
		return  ResponseEntity.ok().body(user.get().getWishlist());
	}
}
