package com.quangta.service;

import com.quangta.entity.Category;
import com.quangta.entity.Food;
import com.quangta.entity.Restaurant;
import com.quangta.payload.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(
            Long restaurantId,
            boolean isVegetarian,
            boolean isNonVegetarian,
            boolean isSeasonal,
            String foodCategory
    );

    public List<Food> searchFood(String keyword);

    public Food getFoodById(Long foodId) throws Exception;

    public Food updateAvailableStatus(Long foodId) throws Exception;
}
