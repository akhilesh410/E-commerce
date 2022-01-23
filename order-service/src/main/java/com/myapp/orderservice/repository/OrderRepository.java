package com.myapp.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.orderservice.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
