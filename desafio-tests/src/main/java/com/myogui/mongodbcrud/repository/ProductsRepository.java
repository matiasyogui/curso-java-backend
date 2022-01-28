package com.myogui.mongodbcrud.repository;

import com.myogui.mongodbcrud.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends MongoRepository<Product, String> {
    Product findAllByNombre(String name);
    List<Product> findAllByCategoria(String category);
    void deleteByNombre(String nombre);
}
