package com.spring.orderMgmnt.service;

import org.springframework.http.ResponseEntity;

import com.spring.orderMgmnt.entity.Order;

public interface OrderService {

	ResponseEntity<?> createOrder(Order order);

}
