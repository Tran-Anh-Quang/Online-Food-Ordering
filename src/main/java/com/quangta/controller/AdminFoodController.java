package com.quangta.controller;

import com.quangta.entity.Food;
import com.quangta.entity.Restaurant;
import com.quangta.entity.User;
import com.quangta.payload.CreateFoodRequest;
import com.quangta.payload.MessageResponse;
import com.quangta.service.FoodService;
import com.quangta.service.RestaurantService;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    private final FoodService foodService;

    private final UserService userService;

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(
            @RequestBody CreateFoodRequest request,
            @RequestHeader("Authorization") String jwtToken
            ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.getRestaurantById(request.getRestaurantId());
        Food food = foodService.createFood(request, request.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        foodService.deleteFood(id);

        MessageResponse response = new MessageResponse();
        response.setMessage("Delete food successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailableStatus(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Food food = foodService.updateAvailableStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
