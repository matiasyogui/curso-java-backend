package com.myogui.restaurants.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myogui.restaurants.model.Restaurant;
import com.myogui.restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;
    private final ObjectMapper mapper;

    @PostConstruct
    private void PostConstruct() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    String mapperToString(Restaurant restaurant) throws JsonProcessingException {
        var restaurantString = mapper.writeValueAsString(restaurant);
        log.info("Mensaje en formato String : {}", restaurantString);
        return restaurantString;
    }

    Map mapperToMap(Restaurant restaurant) throws JsonProcessingException {
        var restaurantString = mapper.writeValueAsString(restaurant);
        var restaurantMap = mapper.readValue(restaurantString, Map.class);
        log.info("Mensaje en formato de Mapa : {}", restaurantMap);
        return restaurantMap;
    }

    void mapperToClass(Restaurant restaurant) throws JsonProcessingException {
        var restaurantString = mapper.writeValueAsString(restaurant);
        var restaurantClass = mapper.readValue(restaurantString, Restaurant.class);
        log.info("Mensaje en formato de Clase : {}", restaurantClass);
    }

    @Override
    public String createRestaurantSerializado(Restaurant restaurant) {
        String restaurantConvered = "";
        try{
            restaurantConvered = mapperToString(restaurant);
            log.info("Mensaje en formato String : {}", restaurantConvered);
        } catch(JsonProcessingException error) {
            log.error("Error converting restaurant to String.", error);
        }

        return restaurantConvered;
    }

    @Override
    public String createRestaurantMap(String restaurant) {
        try{
            var restaurantMap = mapper.readValue(restaurant, Map.class);
            log.info("Mensaje en formato de Mapa : {}", restaurantMap);
        } catch(JsonProcessingException error) {
            log.error("Error converting restaurant to String.", error);
        }

        return restaurant;
    }
}
