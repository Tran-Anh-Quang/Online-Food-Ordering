package com.quangta.repository;

import com.quangta.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findOrderByCustomerId(Long userId);

    public List<Order> findOrderByRestaurantId(Long restaurantId);
}
