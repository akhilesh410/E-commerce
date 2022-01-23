package com.myapp.orderservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.orderservice.domain.Item;
import com.myapp.orderservice.domain.Order;
import com.myapp.orderservice.domain.Product;
import com.myapp.orderservice.domain.User;
import com.myapp.orderservice.feignclient.ProductClient;
import com.myapp.orderservice.feignclient.UserClient;
import com.myapp.orderservice.repository.OrderRepository;
import com.myapp.orderservice.utilities.OrderUtilities;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductClient productclient;
@Autowired
private UserClient userclient;

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order saveOrder(Long userId, Long productId, int quantity) {
		List<Order> orders = new ArrayList<>();
	User user=userclient.getUserById(userId);
		
        if(user==null) {
        	
        }
		Order order = new Order();
		if (productclient.getProductById(productId) == null) {
			
		}
			Product product = productclient.getProductById(productId);

			List<Item> items = new ArrayList<>();
			order.setTotal(OrderUtilities.countTotalPrice(items));
			order.setOrderedDate(LocalDate.now());
			order.setStatus("PAYMENT_EXPECTED");
			order.setUser(user);
			order.setItems(items);
			orders.add(order);
			Item item = new Item();
			item.setProduct(product);
			item.setQuantity(quantity);
			item.setSubTotal(product.getPrice().multiply(new BigDecimal(quantity)));
			item.setOrders(orders);
			items.add(item);
    
		
		return order;

	}
}
