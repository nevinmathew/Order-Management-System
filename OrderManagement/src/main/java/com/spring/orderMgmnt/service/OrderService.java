package com.spring.orderMgmnt.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.spring.orderMgmnt.entity.Order;

public interface OrderService {

	CompletableFuture<?> createOrder(Order order);

	CompletableFuture<?> updateOrder(Order order);

	CompletableFuture<?> getOrder(int id);

	List<Order> getAllOrders();

	String deleteOrder(int id);

}
