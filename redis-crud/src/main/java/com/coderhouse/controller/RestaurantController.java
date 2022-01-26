package com.coderhouse.controller;

import com.coderhouse.model.Restaurant;
import com.coderhouse.service.RestaurantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;
    private final ObjectMapper mapper;

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        log.info("GET obtener mensaje por el id " + id);
        return service.getRestaurantById(id);
    }

    @PostMapping("/mappeado")
    public String createRestaurantMappeado(@RequestBody String restaurant) {
        return service.createRestaurantMap(restaurant);
    }

    @PatchMapping("/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Long id) {
        log.info("UPDATE de restaurante " + newRestaurant.getId());
        return service.update(newRestaurant, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        log.info("DELETE del restaurante " + id);
        service.deleteById(id);
    }

    @PostMapping("/")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        log.info("POST crear mensaje");
        return service.create(restaurant);
    }

    @PostMapping("/serializado")
    public String createRestaurantSerializado(@RequestBody Restaurant restaurant) {
        return service.createRestaurantSerializado(restaurant);
    }
}
