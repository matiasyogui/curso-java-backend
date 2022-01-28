package com.myogui.mongodbcrud.repository;

import com.myogui.mongodbcrud.model.Product;

import java.util.List;

public interface ProductsTemplateRepository {
    List<Product> findAllByPriceSortedLimit(String category, String orderBy, int limit);
    void updateStockByName(Product product, String name);
}
