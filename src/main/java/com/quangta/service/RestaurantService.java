package com.quangta.service;

import com.quangta.dto.RestaurantDto;
import com.quangta.entity.Restaurant;
import com.quangta.entity.User;
import com.quangta.payload.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest request, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest request) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant getRestaurantById(Long restaurantId) throws Exception;

    public Restaurant getRestaurantByOwnerId(Long ownerId) throws Exception;

    public RestaurantDto addRestaurantToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
