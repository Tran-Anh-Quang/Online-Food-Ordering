package com.quangta.controller;

import com.quangta.entity.Food;
import com.quangta.entity.User;
import com.quangta.service.FoodService;
import com.quangta.service.RestaurantService;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService foodService;

    private final UserService userService;

    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFoodById(
            @RequestParam(required = false) boolean isVegetarian,
            @RequestParam(required = false) boolean isSeasonal,
            @RequestParam(required = false) boolean isNonVegetarian,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        List<Food> foods = foodService.getRestaurantFood(restaurantId, isVegetarian, isNonVegetarian, isSeasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
