package com.myapp.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myapp.orderservice.domain.Item;
import com.myapp.orderservice.domain.Order;
import com.myapp.orderservice.domain.User;
import com.myapp.orderservice.service.OrderService;
import com.myapp.orderservice.utilities.OrderUtilities;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/order/")
	public ResponseEntity<Order> saveOrder(@RequestParam("userId") Long userId,
			@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {

		try {

			Order order = orderService.saveOrder(userId, productId, quantity);

			return new ResponseEntity<Order>(order, HttpStatus.CREATED);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
