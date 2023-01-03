package com.spring.orderMgmnt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Override
	public ResponseEntity<?> createOrder(Order order) {
		try {
			if (order.getQuantity() > 0 && customerRepository.existsById(order.getCustomer().getId())) {
				order.setDiscountClaimed(false);
				orderRepository.save(order);
		}

			Customer customer = customerRepository.findById(order.getCustomer().getId()).orElseGet(null);
			int ordersNo = customer.getNoOfOrders();
			customer.setNoOfOrders(++ordersNo);
			customerService.updateCustomer(customer);

			return ResponseEntity.ok("Your order has been placed");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> updateOrder(Order order) {
		try {
			if (order.getQuantity() > 0 && orderRepository.existsById(order.getId())
					&& customerRepository.existsById(order.getCustomer().getId()))
				orderRepository.save(order);

			return ResponseEntity.ok("Your order has been placed");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> getOrder(int id) {
		try {
			if (!orderRepository.existsById(id)) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(orderRepository.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> getAllOrders() {
		try {
			return ResponseEntity.ok(orderRepository.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> deleteOrder(int id) {
		try {
			if (!orderRepository.existsById(id)) {
				return ResponseEntity.noContent().build();
			}
			orderRepository.deleteById(id);
			return ResponseEntity.ok("Your order has been deleted");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

}
