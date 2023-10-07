package com.spring.orderMgmnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String createCustomer(Customer customer) {
		
		if(customer.getUserName()!=null && !customer.getUserName().isBlank())
			customerRepository.save(customer);
		
		return "Customer created";
	}

	@Override
	public String updateCustomer(Customer customer) {

		if (!customerRepository.existsById(customer.getId())) {
			return "User not found";
		}

		String userTier = null;
		Customer cust = Customer.getInstance();
		if (customer.getId() != null)
			cust.setId(customer.getId());

		if (customer.getUserName() != null && !customer.getUserName().isBlank())
			cust.setUserName(customer.getUserName());

		cust.setNoOfOrders(customer.getNoOfOrders() != null ? customer.getNoOfOrders() : Integer.valueOf("0"));

		if (customer.getTier() != null && !customer.getTier().isBlank()) {

			if (Integer.compare(customer.getNoOfOrders() == null ? 0 : customer.getNoOfOrders(), 10) == -1)
				cust.setTier(customer.getTier());

			else if (Integer.compare(customer.getNoOfOrders(), 10) >= 0
					&& Integer.compare(customer.getNoOfOrders(), 20) == -1 && !"Gold".equals(customer.getTier())) {
				cust.setTier("Gold");
				userTier = "You have been upgraded to Gold tier";
			}

			else if (Integer.compare(customer.getNoOfOrders(), 20) >= 0 && !"Platinum".equals(customer.getTier())) {
				cust.setTier("Platinum");
				userTier = "You have been upgraded to Platinum tier";
			}
		}

		customerRepository.save(cust);

		return "Customer updated. " + userTier;
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(int id) {

		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public String deleteCustomer(int id) {
		if (!customerRepository.existsById(id)) {
			return "User not found";
		}

		customerRepository.deleteById(id);
		return "Customer deleted";
	}

	// cron scheduled for 12:01 am, every day, on any of the week
	@Scheduled(cron = "0 1 0 * * ?", zone = "Asia/Colombo")
	@Override
	public ResponseEntity<?> tierBarrierMailSender() {
		// to get the username of the logged in user.
		String username = "username";
		/*
		 * (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		 * ;
		 */
		Customer customer = customerRepository.findByUserName(username);

		if (customer.getNoOfOrders() == 9)
			System.out.println("You have placed 9 orders with us. Place one more and you will be promoted to Gold customer and enjoy 10% discount");

		if (customer.getNoOfOrders() == 19)
			System.out.println("You have placed 19 orders with us. Place one more and you will be promoted to Platinum customer and enjoy 20% discount");

		return ResponseEntity.ok("mail sent to the user");
	}

}
