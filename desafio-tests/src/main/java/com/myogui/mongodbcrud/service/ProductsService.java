package com.myogui.mongodbcrud.service;

import com.myogui.mongodbcrud.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductsService {
    Product createProduct(Product product);
    Product findAllByName(String name);
    List<Product> findAll();
    List<Product> findAllByCategory(String category);
    void updateProductStockByName(Product product, String name);
    List<Product> findAllByPriceSortedLimit(String category, String orderBy, int limit);
    void deleteProductByName(String name);
}
