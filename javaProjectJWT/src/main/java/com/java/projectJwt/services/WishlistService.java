package com.java.projectJwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.projectJwt.models.Product;
import com.java.projectJwt.models.User;
import com.java.projectJwt.models.Wishlist;
import com.java.projectJwt.repositories.ProductRepo;
import com.java.projectJwt.repositories.UserRepository;
import com.java.projectJwt.repositories.WishlistRepository;

@Service
public class WishlistService {
	// === CRUD ===
	
	@Autowired
	private WishlistRepository whishlistRepo;
	@Autowired
	private UserRepository userRepo;
	
	// READ ALL
		public List<Wishlist> allProduct(){
			return whishlistRepo.findAll();
		}
		
		// CREATE
		public Wishlist create(Wishlist p) {
			System.out.println(p);
			User  user=userRepo.getById((long) 1);
			p.setUser(user);
			return whishlistRepo.save(p);
		}
		
		// READ ONE
		public Optional<Wishlist> findOne(Long id) {
			
			Optional<Wishlist> maybeProduct = whishlistRepo.findById(id);
			if(maybeProduct.isPresent()) {
				return maybeProduct;
			}else {
				return null;
			}
		}
		
		// UPDATE 
		public Wishlist update(Wishlist p) {
			return whishlistRepo.save(p);
		}
		
		// DELETE
		public void delete(Long id) {
			whishlistRepo.deleteById(id);
		}
	
}
