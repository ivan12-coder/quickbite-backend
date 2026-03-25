package com.quickbite.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "cuisine_type")
    private String cuisineType;

    private String address;
    private String phone;
    private String email;
    private Double rating;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(name = "min_order_amount")
    private BigDecimal minOrderAmount;

    @Column(name = "estimated_delivery_time")
    private String estimatedDeliveryTime;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (rating == null) rating = 0.0;
        if (deliveryFee == null) deliveryFee = BigDecimal.ZERO;
    }
}