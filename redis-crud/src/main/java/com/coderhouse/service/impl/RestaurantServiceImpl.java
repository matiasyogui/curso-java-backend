package com.coderhouse.service.impl;

import com.coderhouse.cache.CacheClient;
import com.coderhouse.handle.ApiRestException;
import com.coderhouse.model.Restaurant;
import com.coderhouse.repository.RestaurantRepository;
import com.coderhouse.service.RestaurantService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;
    private final CacheClient<Restaurant> cache;
    private final ObjectMapper mapper;

    @Override
    public Restaurant create(Restaurant restaurant) {
        try {
            var data = repository.save(restaurant);
            return saveRestaurantInCache(data);
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurant to string", e);
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        try {
            if (id == 0) {
                throw ApiRestException.builder().message("El identificador del restaurante debe ser mayor a 0").build();
            }
            var dataFromCache = cache.recover(id.toString(), Restaurant.class);
            if (!Objects.isNull(dataFromCache)) {
                return dataFromCache;
            }
            var dataFromDatabase = repository.findById(id).orElseThrow(ApiRestException::new);
            return saveRestaurantInCache(dataFromDatabase);
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurant to string", e);
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (id == 0) {
                throw ApiRestException.builder().message("El identificador del restaurante debe ser mayor a 0").build();
            }
            var dataFromCache = cache.recover(id.toString(), Restaurant.class);
            if (!Objects.isNull(dataFromCache)) {
                repository.deleteById(id);
            }
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant update(Restaurant restaurant, Long id) {
        try {
            if (id == 0) {
                throw ApiRestException.builder().message("El identificador del restaurante debe ser mayor a 0").build();
            }
            var dataFromCache = cache.recover(id.toString(), Restaurant.class);
            if (!Objects.isNull(dataFromCache)) {
                repository.deleteById(id);
                var data = repository.save(restaurant);
                return saveRestaurantInCache(data);
            }
            var dataFromDatabase = repository.findById(id).orElseThrow(ApiRestException::new);
            return saveRestaurantInCache(dataFromDatabase);
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurant to string", e);
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Restaurant saveRestaurantInCache(Restaurant restaurant) throws JsonProcessingException {
        return cache.save(restaurant.getId().toString(), restaurant);
    }

    //METODOS PARA LOS POSTS SERIALIZADOS

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
