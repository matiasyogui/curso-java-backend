package com.myoguicoderhouse.controlleradvice.controller;

import com.myoguicoderhouse.controlleradvice.handle.ApiRestException;
import com.myoguicoderhouse.controlleradvice.model.ProductModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path="/api")
public class ProductController {
    List<ProductModel> products = new ArrayList<>();
    Integer ids = 0;

    @GetMapping("/products")
    public List<ProductModel> getProducts() {
        if(products.isEmpty()) {
            List<ProductModel> productsError = new ArrayList<>();
            productsError.add(new ProductModel("No hay productos", 0));
            return productsError;
        }
        return products;
    }

    @GetMapping("/products/{id}")
    public ProductModel getProductById(@PathVariable Integer id) throws ApiRestException {
        if(id == 0) {
            throw new ApiRestException("Id must be greater than 0.");
        }
        var filteredProduct = products.stream().filter(product -> Objects.equals(product.getId(), id));

        return filteredProduct.findFirst().orElse(new ProductModel("No se encontro producto", 0));
    }

    @PostMapping("/products")
    public ProductModel createProduct(@RequestBody ProductModel product) {
        product.setId(ids);
        ids = ids + 1;

        products.add(product);

        return product;
    }
}
