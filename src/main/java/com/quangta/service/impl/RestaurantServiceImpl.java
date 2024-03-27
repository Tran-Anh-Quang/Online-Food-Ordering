package com.quangta.service.impl;

import com.quangta.dto.RestaurantDto;
import com.quangta.entity.Address;
import com.quangta.entity.Restaurant;
import com.quangta.entity.User;
import com.quangta.payload.CreateRestaurantRequest;
import com.quangta.repository.AddressRepository;
import com.quangta.repository.RestaurantRepository;
import com.quangta.repository.UserRepository;
import com.quangta.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final AddressRepository addressRepository;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        Address address = addressRepository.save(request.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest request) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);

        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(request.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(request.getDescription());
        }
        if (restaurant.getName() != null) {
            restaurant.setName(request.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isEmpty()){
            throw new Exception("Restaurant not found");
        }
        return restaurantOptional.get();
    }

    @Override
    public Restaurant getRestaurantByOwnerId(Long ownerId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(ownerId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found with userId " + ownerId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addRestaurantToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurant.getId());

        boolean isFavorited = false;
        List<RestaurantDto> favorites = user.getFavorites();
        for (RestaurantDto favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }

        if (isFavorited) {
            favorites.removeIf(f -> f.getId().equals(restaurantId));
        }else {
            favorites.add(restaurantDto);
        }
        userRepository.save(user);

        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
