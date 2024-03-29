package com.quangta.controller;

import com.quangta.entity.Restaurant;
import com.quangta.entity.User;
import com.quangta.payload.request.CreateRestaurantRequest;
import com.quangta.payload.response.MessageResponse;
import com.quangta.service.RestaurantService;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    private final RestaurantService restaurantService;

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String jwtToken
            ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Restaurant restaurant = restaurantService.createRestaurant(request, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Restaurant restaurant = restaurantService.updateRestaurant(id, request);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        restaurantService.deleteRestaurant(id);

        MessageResponse response = new MessageResponse();
        response.setMessage("delete restaurant successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> getRestaurantByOwnerId(
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Restaurant restaurant = restaurantService.getRestaurantByOwnerId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
