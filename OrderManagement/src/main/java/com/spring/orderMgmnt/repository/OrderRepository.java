package com.spring.orderMgmnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.orderMgmnt.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {

}
