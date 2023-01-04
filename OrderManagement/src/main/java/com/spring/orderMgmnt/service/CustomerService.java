package com.spring.orderMgmnt.service;

import org.springframework.http.ResponseEntity;

import com.spring.orderMgmnt.entity.Customer;

public interface CustomerService {
	
	public ResponseEntity<?> createCustomer(Customer customer);
	
	public ResponseEntity<?> updateCustomer(Customer customer);
	
	public ResponseEntity<?> getAllCustomer();
	
	public ResponseEntity<?> getCustomer(int id);
	
	public ResponseEntity<?> deleteCustomer(int id);
	
	public ResponseEntity<?> tierBarrierMailSender();

}
