package com.java.projectJwt.controllers;

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

import com.java.projectJwt.models.Order;
import com.java.projectJwt.services.OrderService;

import jakarta.validation.Valid;
@RequestMapping("/api/v1")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderApi {

	
private final OrderService orderservice;

	public OrderApi(OrderService orderservice) {
		this.orderservice=orderservice;
	}
	@GetMapping("/Orders")
	public ResponseEntity<Object> index() {
		
		return ResponseEntity.ok().body(orderservice.allOrder());
	}
	
	
	@PostMapping("/orders")
	public ResponseEntity<Object> create (@Valid @RequestBody Order order, BindingResult result){
		
		Order create = orderservice.create(order);
		
		return  ResponseEntity.ok().body(create);
	}
	
	@GetMapping("/orders/{id}")
	public Order show(@PathVariable Long id) {
		return orderservice.findOne(id).orElseThrow(RuntimeException::new);
	}
	


	@PutMapping("/orders/{id}/{prod_id}")
	public ResponseEntity<Object> update(@Valid @RequestBody Order order, BindingResult result,@PathVariable("id") Long id,@PathVariable("prod_id") Long prod_id ) {
    
	  	if(result.hasErrors()) {
    		return ResponseEntity.status(400).body(result.getAllErrors());
    	}
	  	order.setId(id);
    	Order orderUpdate=orderservice.update(order,prod_id);
			return ResponseEntity.ok().body(orderUpdate);
		}

	
	// DELETE
	
	@DeleteMapping("/Orders/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		orderservice.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
	
}
