package com.spring.orderMgmnt.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.orderMgmnt.entity.Customer;

public interface CustomerService {
	
	public String createCustomer(Customer customer);
	
	public String updateCustomer(Customer customer);
	
	public List<Customer> getAllCustomer();
	
	public Customer getCustomer(int id);
	
	public String deleteCustomer(int id);
	
	public ResponseEntity<?> tierBarrierMailSender();

}
