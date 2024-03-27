package com.quangta.controller;

import com.quangta.dto.RestaurantDto;
import com.quangta.entity.Restaurant;
import com.quangta.entity.User;
import com.quangta.service.RestaurantService;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam String keyword
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        List<Restaurant> restaurants = restaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Restaurant restaurants = restaurantService.getRestaurantById(id);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-to-favorites")
    public ResponseEntity<RestaurantDto> addRestaurantToFavorites(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        RestaurantDto restaurantDto = restaurantService.addRestaurantToFavorites(id, user);

        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }
}
