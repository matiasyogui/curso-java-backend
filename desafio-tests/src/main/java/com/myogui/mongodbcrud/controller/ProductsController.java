package com.myogui.mongodbcrud.controller;

import com.myogui.mongodbcrud.model.Product;
import com.myogui.mongodbcrud.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongodbcrud")
public class ProductsController {
    @Autowired
    ProductsService service;

    @GetMapping("/products")
    public List<Product> findProducts() {
        return service.findAll();
    }

    @GetMapping("/products/{category}")
    public List<Product> findProductsByCategory(@PathVariable String category) {
        return service.findAllByCategory(category);
    }

    @GetMapping("/products/name/{name}")
    public Product findByName(@PathVariable String name) {
        return service.findAllByName(name);
    }

    @GetMapping("/products/all")
    public List<Product> findProductsByCategorySortedByPrice(@RequestParam String category,
                                                             @RequestParam String orderBy,
                                                             @RequestParam int limit) {
        return service.findAllByPriceSortedLimit(category, orderBy, limit);
    }

    @PostMapping("/products")
    public Product createProduct (@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PatchMapping("/products/{name}")
    public void updateProductStock(@RequestBody Product product, @PathVariable String name) {
        service.updateProductStockByName(product, name);
    }

    @DeleteMapping("/products/{name}")
    public void deleteProductByName(@PathVariable String name) {
        service.deleteProductByName(name);
    }
}
