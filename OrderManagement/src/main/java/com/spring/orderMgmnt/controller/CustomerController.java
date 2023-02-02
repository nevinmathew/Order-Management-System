package com.spring.orderMgmnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.orderMgmnt.entity.Customer;
import com.spring.orderMgmnt.service.CustomerService;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create-employee")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
		try {
			return ResponseEntity.ok(customerService.createCustomer(customer));
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
		}
	}
	
	@PutMapping("/update-employee")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
		try {
			return ResponseEntity.ok(customerService.updateCustomer(customer));
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/get-employee/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable(name = "id") int id){
		try {
			return ResponseEntity.ok(customerService.getCustomer(id));
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/get-employee")
	public ResponseEntity<?> getAllCustomer(){
		try {
			return ResponseEntity.ok(customerService.getAllCustomer());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
		}
	}
	
	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") int id){
		try {
			return ResponseEntity.ok(customerService.deleteCustomer(id));
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
		}
	}
}
