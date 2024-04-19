package com.quangta.service;

import com.quangta.entity.Order;
import com.quangta.entity.User;
import com.quangta.payload.request.OrderRequest;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderRequest orderRequest, User user) throws Exception;

     Order updateOrder(Long orderId, String orderStatus) throws Exception;

     void cancelOrder(Long orderId) throws Exception;

     List<Order> getUsersOrder(Long userId) throws Exception;

     List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception;

     Order findOrderById(Long orderId) throws Exception;
}
