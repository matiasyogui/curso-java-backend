package com.coderhouse.builder;

import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.database.document.concrete.UserAdmin;
import com.coderhouse.model.database.document.concrete.UserClient;
import com.coderhouse.model.database.document.concrete.UserEditor;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserBuilder {

    public static UserEditor requestToEntityEditor(UserRequest request) {
        return UserEditor.builder()
                .type(request.getType())
                .username(request.getUsername())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .telephone(request.getTelephone())
                .createDate(LocalDateTime.now())
                .build();
    }

    public static UserAdmin requestToEntityAdmin(UserRequest request) {
        return UserAdmin.builder()
                .type(request.getType())
                .username(request.getUsername())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .telephone(request.getTelephone())
                .createDate(LocalDateTime.now())
                .build();
    }

    public static UserClient requestToEntityClient(UserRequest request) {
        return UserClient.builder()
                .type(request.getType())
                .username(request.getUsername())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .telephone(request.getTelephone())
                .createDate(LocalDateTime.now())
                .build();
    }

    public static UserResponse entityToResponseCreate(UserDocument entity) {
        return UserResponse.builder()
                .code(entity.getId())
                .build();
    }


    public static <T extends UserDocument> UserResponse entityToResponse(T entity) {
        return UserResponse.builder()
                .code(entity.getId())
                .type(entity.getType())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .fullName(entity.getFullName())
                .telephone(entity.getTelephone())
                .createDate(entity.getCreateDate())
                .build();
    }

    public static List<UserResponse>
    listEntityToResponse(List<UserDocument> users) {

        var listResponse = new ArrayList<UserResponse>();
        users.forEach(item -> listResponse.add(entityToResponse(item)));
        return listResponse;
    }

}
