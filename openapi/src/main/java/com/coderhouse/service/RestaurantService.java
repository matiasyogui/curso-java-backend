package com.coderhouse.service;

import com.coderhouse.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);
    List<Restaurant> findAll();
    Restaurant getRestaurantById(Long id);
    void deleteById(Long id);
    Restaurant update(Restaurant restaurant, Long id);

    String createRestaurantSerializado(Restaurant restaurant);
    String createRestaurantMap(String restaurant);
}
