package com.myapp.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.orderservice.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
