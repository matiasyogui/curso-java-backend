package com.myogui.restaurants.service;

import com.myogui.restaurants.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    String createRestaurantSerializado(Restaurant restaurant);
    String createRestaurantMap(String restaurant);
}
