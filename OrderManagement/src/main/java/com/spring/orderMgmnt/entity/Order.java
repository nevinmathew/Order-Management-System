package com.spring.orderMgmnt.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@Column(name = "customer_id")
//	private String customerId;
	
	@Column(name = "qty")
	private int quantity;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "discount")
	private BigDecimal discount;
	
	private Order() {}
	
	//BillPugh Singleton implementation
	private static class SingletonHelper{
		private static final Order INSTANCE = new Order();
	}
	
	public static Order getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public String getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@ManyToOne
	public Customer customer;
	
}
