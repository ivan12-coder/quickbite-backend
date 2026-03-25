package com.quickbite.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "category_id")
    private Long categoryId;

    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    @Column(name = "is_available")
    private Boolean isAvailable = true;
}