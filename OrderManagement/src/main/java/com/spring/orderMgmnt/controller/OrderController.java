package com.spring.orderMgmnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.orderMgmnt.entity.Order;
import com.spring.orderMgmnt.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/place-order")
	public ResponseEntity<?> placeOrder(@RequestBody Order order){
		try {
			return ResponseEntity.ok(orderService.createOrder(order));
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@PutMapping("/update-order")
	public ResponseEntity<?> updateOrder(@RequestBody Order order){
		try {
			return ResponseEntity.ok(orderService.updateOrder(order));
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/get-order")
	public ResponseEntity<?> getOrder(int id){
		try {
			return ResponseEntity.ok(orderService.getOrder(id));
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/get-all-orders")
	public ResponseEntity<?> getAllOrders(){
		try {
			return ResponseEntity.ok(orderService.getAllOrders());
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@DeleteMapping("/delete-order")
	public ResponseEntity<?> deleteOrder(int id){
		try {
			return ResponseEntity.ok(orderService.deleteOrder(id));
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
}
