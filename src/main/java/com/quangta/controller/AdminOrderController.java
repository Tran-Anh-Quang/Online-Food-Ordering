package com.quangta.controller;

import com.quangta.entity.Order;
import com.quangta.entity.User;
import com.quangta.service.OrderService;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminOrderController {

    private final OrderService orderService;

    private final UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        List<Order> orders = orderService.getRestaurantOrder(id,order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/{order_status}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String order_status,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        Order order = orderService.updateOrder(orderId,order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
