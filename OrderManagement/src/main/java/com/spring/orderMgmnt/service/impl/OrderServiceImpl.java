package com.spring.orderMgmnt.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.spring.orderMgmnt.entity.Customer;
import com.spring.orderMgmnt.entity.Order;
import com.spring.orderMgmnt.repository.CustomerRepository;
import com.spring.orderMgmnt.repository.OrderRepository;
import com.spring.orderMgmnt.service.CustomerService;
import com.spring.orderMgmnt.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private Executor asyncExecutor;

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<?> createOrder(Order order) {
//		try {
			return CompletableFuture.supplyAsync(() -> {
	            if (order == null || order.getCustomer() == null) {
	                return "Invalid order";
	            }
	            
	            Optional<Customer> customer = customerRepository.findById(order.getCustomer().getId());
	            if (order.getQuantity() <= 0 || !customer.isPresent()) {
	                return "Invalid order details";
	            }

                int ordersNo = customer.get().getNoOfOrders();
                customer.get().setNoOfOrders(++ordersNo);
                customerService.updateCustomer(customer.get());

                return "Your order has been placed";
	        }, asyncExecutor).exceptionally(e -> {
	            e.printStackTrace();
	            return "Order creation failed";
	        });
				
//				CompletableFuture<?> future = new CompletableFuture<>();
//				if (order.getQuantity() > 0 && customerRepository.existsById(order.getCustomer().getId())) {
//					order.setDiscountClaimed(false);
//				
//					Thread.sleep(1000L);
//					CompletableFuture.completedFuture(orderRepository.save(order));
//				}
//				
//				Customer customer = customerRepository.findById(order.getCustomer().getId()).orElseGet(null);
//				if (customer.getNoOfOrders() != null) {
//					int ordersNo = customer.getNoOfOrders();
//					customer.setNoOfOrders(++ordersNo);
//				}
//				customerService.updateCustomer(customer);
				
//				return CompletableFuture.runAsync(() -> {
//		            Customer customer = customerRepository.findById(order.getCustomer().getId()).get();
//		            if (customer != null) {
//		                int ordersNo = customer.getNoOfOrders();
//		                customer.setNoOfOrders(++ordersNo);
//		                customerService.updateCustomer(customer);
//		            } else {
//		                throw new RuntimeException("Customer not found");
//		            }
//		        }).thenApplyAsync(
//		            result -> new ResponseEntity<>("Your order has been placed", HttpStatus.CREATED)
//		        );
				
//				return future.thenApply(result -> new ResponseEntity<>("Your order has been placed", HttpStatus.CREATED));
//				return CompletableFuture.completedFuture(ResponseEntity.ok("Your order has been placed"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			CompletableFuture<?> future = new CompletableFuture<>();
//			return future.thenApply(result -> new ResponseEntity<>(null, HttpStatus.FORBIDDEN));
//		}
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<String> updateOrder(Order order) {
		
		try {
			if (order.getQuantity() > 0 && orderRepository.existsById(order.getId())
					&& customerRepository.existsById(order.getCustomer().getId())) {
			
				return CompletableFuture.supplyAsync(() -> {
					orderRepository.save(order);
					return "Your order has been updated";
			}, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS, asyncExecutor));
		
		} else {
			return CompletableFuture.completedFuture("Invalid order or customer");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Order update failed", e);
		}
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<Order> getOrder(int id) {
//		try {
//			CompletableFuture<?> future = new CompletableFuture<>();
//			if (!orderRepository.existsById(id)) {
//				return future.thenApply(result -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
//			}
//			Thread.sleep(1000L);
//			return future.thenApply(result -> new ResponseEntity<>(orderRepository.findById(id), HttpStatus.CREATED));
//		} catch (Exception e) {
//			e.printStackTrace();
//			CompletableFuture<?> future = new CompletableFuture<>();
//			return future.thenApply(result -> new ResponseEntity<>(null, HttpStatus.FORBIDDEN));
//		}
		try {
	        if (id <= 0) {
	            throw new IllegalArgumentException("Invalid order ID");
	        }

	        if (!orderRepository.existsById(id)) {
	            throw new IllegalArgumentException("Order not found");
	        }

	        return CompletableFuture
	        		.supplyAsync(() -> orderRepository.findById(id).get(), asyncExecutor)
	                .orTimeout(1, TimeUnit.SECONDS);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Order retrieval failed", e);
	    }
	}

	@Override
	public List<Order> getAllOrders() {
			return orderRepository.findAll();
	}

	@Override
	public String deleteOrder(int id) {
			if (!orderRepository.existsById(id)) {
				return "The order does not exist";
			}
			orderRepository.deleteById(id);
			return "Your order has been deleted";
	}

}
