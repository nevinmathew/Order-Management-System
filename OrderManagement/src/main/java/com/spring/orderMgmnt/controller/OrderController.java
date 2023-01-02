package com.spring.orderMgmnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	public ResponseEntity<?> placeOrder(@RequestBody Order order){
		try {
			return ResponseEntity.ok(orderService.createOrder(order));
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

}
