package com.quangta.service.impl;

import com.quangta.entity.Category;
import com.quangta.entity.Food;
import com.quangta.entity.Restaurant;
import com.quangta.payload.CreateFoodRequest;
import com.quangta.repository.FoodRepository;
import com.quangta.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(request.getDescription());
        food.setImages(request.getImages());
        food.setName(request.getName());
        food.setPrice(request.getPrice());
        food.setIngredients(request.getIngredients());
        food.setSeasonal(request.isSeasonal());
        food.setVegetarian(request.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = getFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(
            Long restaurantId,
            boolean isVegetarian,
            boolean isNonVegetarian,
            boolean isSeasonal,
            String foodCategory
    ) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian){
            foods = filterByVegetarian(foods);
        }
        if (isNonVegetarian){
            foods = filterByNonVegetarian(foods);
        }
        if (isSeasonal){
            foods = filterBySeasonal(foods);
        }
        if (foodCategory != null && !foodCategory.isEmpty()) {
            foods = filterByCategory(foods, foodCategory);
        }
        return null;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if (food.getFoodCategory() != null) {
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }


    private List<Food> filterBySeasonal(List<Food> foods) {
        return foods.stream().filter(Food::isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods) {
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods){
        return foods.stream().filter(Food::isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food getFoodById(Long foodId) throws Exception {
        Optional<Food> foodOptional = foodRepository.findById(foodId);

        if (foodOptional.isEmpty()){
            throw new Exception("Food not found");
        }
        return foodOptional.get();
    }

    @Override
    public Food updateAvailableStatus(Long foodId) throws Exception {
        Food food = getFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
