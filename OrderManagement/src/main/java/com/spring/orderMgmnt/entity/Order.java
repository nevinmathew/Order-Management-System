package com.spring.orderMgmnt.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "product")
	private String product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "discount")
	private BigDecimal discount;

	@Column(name = "discount_claimed")
	private Boolean discountClaimed;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

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
	
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public Boolean getDiscountClaimed() {
		return discountClaimed;
	}

	public void setDiscountClaimed(Boolean discountClaimed) {
		this.discountClaimed = discountClaimed;
	}

	@ManyToOne
//	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	public Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
