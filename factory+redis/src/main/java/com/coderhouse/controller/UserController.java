package com.coderhouse.controller;

import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/factory")
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public UserResponse createUser(
            @Validated @RequestBody UserRequest user) {
        return service.create(user);
    }

    @GetMapping("/user/all")
    public List<UserResponse> searchProduct() {
        return service.searchAll();
    }

    @GetMapping("/user/{id}")
    public UserResponse getById(@PathVariable String id) {
        return service.getUserById(id);
    }

    @PutMapping("/user/{id}")
    public UserResponse updateUser(@PathVariable String id, @Validated @RequestBody UserRequest user) {
        return service.update(id, user);
    }
}
