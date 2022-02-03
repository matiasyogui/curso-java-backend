package com.coderhouse.repository;

import com.coderhouse.model.database.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryMongo extends MongoRepository<UserDocument, String> {

}
