package com.coderhouse.service.impl;

import com.coderhouse.builder.UserBuilder;
import com.coderhouse.cache.CacheClient;
import com.coderhouse.cache.CacheClientImpl;
import com.coderhouse.model.UserFactory;
import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.repository.UserRepositoryMongo;
import com.coderhouse.repository.UserRepositoryRedis;
import com.coderhouse.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryMongo mongo;
    private final UserRepositoryRedis redis;
    private final CacheClient<UserResponse> cache;
    private final UserFactory userFactory = new UserFactory();

    @Override
    public UserResponse create(UserRequest request) {
        var entity =
                userFactory.createUser(request);
        var entitySaved = mongo.save(entity);
        return saveUserInCache(UserBuilder.entityToResponseCreate(entitySaved));
    }

    @Override
    public List<UserResponse> searchAll() {
        return UserBuilder.listEntityToResponse(mongo.findAll());
    }

    @Override
    public UserResponse getUserById(String id) {
        var dataFromCache = cache.recover(id, UserResponse.class);
        if (!Objects.isNull(dataFromCache)) {
            return dataFromCache;
        }
        var dataFromDatabase = mongo.findById(id).orElse(null);
        return saveUserInCache(UserBuilder.entityToResponse(dataFromDatabase));
    }

    @Override
    public UserResponse update(String id, UserRequest user) {
        var entity =
                userFactory.createUser(user);
        entity.setId(id);
        var entitySaved = mongo.save(entity);
        return saveUserInCache(UserBuilder.entityToResponse(entitySaved));
    }

    private UserResponse saveUserInCache(UserResponse user) {
        return cache.save(user.getCode(), user);
    }
}
