package com.spring.orderMgmnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.orderMgmnt.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
