package com.myogui.mongodbcrud.service;

import com.myogui.mongodbcrud.model.Product;
import com.myogui.mongodbcrud.repository.ProductsRepository;
import com.myogui.mongodbcrud.repository.ProductsTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepository repository;

    @Autowired
    private ProductsTemplateRepository template;


    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findAllByName(String name) {
        return repository.findAllByNombre(name);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(String category) {
        return repository.findAllByCategoria(category);
    }

    @Override
    public void updateProductStockByName(Product product, String name) {
        template.updateStockByName(product, name);
    }

    @Override
    public List<Product> findAllByPriceSortedLimit(String category, String orderBy, int limit) {
        return template.findAllByPriceSortedLimit(category, orderBy, limit);
    }

    @Override
    public void deleteProductByName(String name) {
        repository.deleteByNombre(name);
    }
}
