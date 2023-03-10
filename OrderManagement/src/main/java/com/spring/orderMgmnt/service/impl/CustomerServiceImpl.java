package com.spring.orderMgmnt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.orderMgmnt.entity.Customer;
import com.spring.orderMgmnt.repository.CustomerRepository;
import com.spring.orderMgmnt.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ResponseEntity<?> createCustomer(Customer customer) {
		try {
			customerRepository.save(customer);
			return ResponseEntity.ok("Customer created");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> updateCustomer(Customer customer) {
		try {
			
			String userTier = null;
			
			if (!customerRepository.existsById(customer.getId())) {
				return ResponseEntity.ok("User not found");
			}

			Customer cust = Customer.getInstance();
			if(customer.getId() != null)
				cust.setId(customer.getId() );
			
			if (!customer.getName().isBlank() || customer.getName() != null)
				cust.setName(customer.getName());

			if (customer.getNoOfOrders() != null)
				cust.setNoOfOrders(Integer.valueOf(customer.getNoOfOrders()));
			else if(customer.getNoOfOrders() == null)
				cust.setNoOfOrders(Integer.valueOf("0"));
			
			if (!customer.getTier().isBlank() || customer.getTier() != null) {
				
				if(Integer.compare(customer.getNoOfOrders()==null ? 0 : customer.getNoOfOrders(), 10) == -1 )
					cust.setTier(customer.getTier());
				
				else if (Integer.compare(customer.getNoOfOrders(),10)>=0 && Integer.compare(customer.getNoOfOrders(),20)==-1
						&& !"Gold".equals(customer.getTier())) {
					cust.setTier("Gold");
					userTier = "You have been upgraded to Gold tier";
				}
				
				else if (Integer.compare(customer.getNoOfOrders(), 20)>=0 && !"Platinum".equals(customer.getTier())) {
					cust.setTier("Platinum");
					userTier = "You have been upgraded to Platinum tier";
				}
			}

			customerRepository.save(cust);

			return ResponseEntity.ok("Customer updated. "+userTier);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> getAllCustomer() {
		try {
			return ResponseEntity.ok(customerRepository.findAll());
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> getCustomer(int id) {
		try {
			
			if(!customerRepository.existsById(id)) {
				return ResponseEntity.ok("User not found");
			}
			return ResponseEntity.ok(customerRepository.findById(id));
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<?> deleteCustomer(int id) {
		try {
			
			if(!customerRepository.existsById(id)) {
				return ResponseEntity.ok("User not found");
			}
			
			customerRepository.deleteById(id);
			return ResponseEntity.ok("Customer deleted");
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	//cron scheduled for 12:01 am, every day, on any of the week
	@Scheduled(cron = "0 1 0 * * ?", zone = "Asia/Colombo")
	@Override
	public ResponseEntity<?> tierBarrierMailSender() {
		//to get the username of the logged in user. Only for understanding purpose
		String username = "username";/*(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/ 
		Customer customer = customerRepository.findByUserName(username);
		
		if(customer.getNoOfOrders() == 9)
			System.out.println("You have placed 9 orders with us. Place one more and you will be promoted to Gold customer and enjoy 10% discount");
		
		if(customer.getNoOfOrders() == 19)
			System.out.println("You have placed 19 orders with us. Place one more and you will be promoted to Platinum customer and enjoy 20% discount");
		
		return ResponseEntity.ok("mail sent to the user");
	}

}
