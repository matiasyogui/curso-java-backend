package com.coderhouse.cache;

import com.coderhouse.Utils.Constants;
import com.coderhouse.config.ApplicationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheClientImpl<T> implements CacheClient<T> {

    private final RedisTemplate<String, T> redisTemplate;
    //private final ApplicationProperties properties;
    private HashOperations<String, String, String> hashOperations;
    private final ObjectMapper mapper;

    @PostConstruct
    void setHashOperations() {
        hashOperations = this.redisTemplate.opsForHash();
        this.redisTemplate.expire(Constants.NAME_MAP_RESTAURANT, Duration.ofMillis(60000));
    }

    @Override
    public T save(String key, T data) {
        try {
            hashOperations.put(Constants.NAME_MAP_RESTAURANT, key, serializeItem(data));
            return data;
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurant to string", e);
        }
        return data;
    }

    @Override
    public T recover(String key, Class<T> classValue) {
        try {
            var item = hashOperations.get(Constants.NAME_MAP_RESTAURANT, key);
            if (item == null) return null;
            return deserializeItem(item, classValue);
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurant to Restaurant", e);
        }
        return null;
    }

    private String serializeItem(T item) throws JsonProcessingException {
        var serializeItem = mapper.writeValueAsString(item);
        log.info("Restaurant en formato String: {}", serializeItem);
        return serializeItem;
    }

    private T deserializeItem(String jsonInput, Class<T> classValue) throws JsonProcessingException {
        return mapper.readValue(jsonInput, classValue);
    }



}
