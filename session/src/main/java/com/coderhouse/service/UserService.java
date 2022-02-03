package com.coderhouse.service;

import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest request);
    List<UserResponse> searchAll();
    UserResponse getUserById(String id);
    UserResponse update(String id, UserRequest user);
}
