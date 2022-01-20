package com.myogui.restaurants.repository;

import com.myogui.restaurants.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
