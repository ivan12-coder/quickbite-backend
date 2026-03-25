package com.quickbite.backend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private String address;
    private Double rating;
    private BigDecimal deliveryFee;  // Must be BigDecimal, not Double
    private String estimatedDeliveryTime;
    private String imageUrl;
    private Boolean isActive;
}