package com.quickbite.backend.repository;

import com.quickbite.backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Find restaurants by cuisine type
    List<Restaurant> findByCuisineType(String cuisineType);

    // Find restaurants by name containing keyword
    List<Restaurant> findByNameContainingIgnoreCase(String keyword);

    // Find active restaurants with rating above certain value
    List<Restaurant> findByIsActiveTrueAndRatingGreaterThanEqual(Double rating);

    // Custom query to search by multiple criteria
    @Query("SELECT r FROM Restaurant r WHERE " +
            "(:cuisine IS NULL OR r.cuisineType = :cuisine) AND " +
            "(:keyword IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Restaurant> searchRestaurants(@Param("cuisine") String cuisine,
                                       @Param("keyword") String keyword);
}