package com.myapp.orderservice.service;

import java.util.List;


import com.myapp.orderservice.domain.Order;

public interface OrderService {
    

	public Order saveOrder(Long userId, Long productId, int quantity);

	
}
