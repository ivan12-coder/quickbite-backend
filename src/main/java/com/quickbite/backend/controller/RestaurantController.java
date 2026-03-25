package com.quickbite.backend.controller;

import com.quickbite.backend.dto.RestaurantDTO;
import com.quickbite.backend.entity.MenuItem;
import com.quickbite.backend.entity.Restaurant;
import com.quickbite.backend.repository.MenuItemRepository;
import com.quickbite.backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        List<RestaurantDTO> dtos = restaurants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(convertToDTO(restaurant));
    }

    @GetMapping("/cuisine/{cuisineType}")
    public ResponseEntity<List<RestaurantDTO>> getByCuisine(@PathVariable String cuisineType) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByCuisine(cuisineType);
        List<RestaurantDTO> dtos = restaurants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDTO>> search(@RequestParam String keyword) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(keyword);
        List<RestaurantDTO> dtos = restaurants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}/menu")
    public ResponseEntity<List<MenuItem>> getRestaurantMenu(@PathVariable Long id) {
        List<MenuItem> menu = menuItemRepository.findByRestaurantId(id);
        return ResponseEntity.ok(menu);
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setAddress(restaurant.getAddress());
        dto.setRating(restaurant.getRating());
        dto.setDeliveryFee(restaurant.getDeliveryFee());
        dto.setEstimatedDeliveryTime(restaurant.getEstimatedDeliveryTime());
        dto.setImageUrl(restaurant.getImageUrl());
        dto.setIsActive(restaurant.getIsActive());
        return dto;
    }
}