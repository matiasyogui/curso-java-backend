package com.coderhouse.controller;

import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.service.UserService;
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
    public UserResponse createProduct(
            @Validated @RequestBody UserRequest product) {
        return service.create(product);
    }

    @GetMapping("/user/all")
    public List<UserResponse> searchProduct() {
        return service.searchAll();
    }
}
