package com.quickbite.backend.service;

import com.quickbite.backend.entity.Restaurant;
import com.quickbite.backend.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType);
    }

    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}