package com.coderhouse.repository;

import com.coderhouse.model.database.document.UserDocument;
import org.apache.tomcat.jni.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryRedis extends CrudRepository<UserDocument, String> {
}
