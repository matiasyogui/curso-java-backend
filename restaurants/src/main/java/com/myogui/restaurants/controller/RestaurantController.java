package com.myogui.restaurants.controller;

import com.myogui.restaurants.model.Restaurant;
import com.myogui.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService service;

    @PostMapping("/serializado")
    public String createRestaurantSerializado(@RequestBody Restaurant restaurant) {
        return service.createRestaurantSerializado(restaurant);
    }

    @PostMapping("/mappeado")
    public String createRestaurantMappeado(@RequestBody String restaurant) {
        return service.createRestaurantMap(restaurant);
    }
}
