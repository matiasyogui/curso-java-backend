package com.myogui.mongodbcrud.repository;

import com.myogui.mongodbcrud.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsTemplateRepositoryImpl implements ProductsTemplateRepository {
    @Autowired
    MongoTemplate template;

    @Override
    public List<Product> findAllByPriceSortedLimit(String category, String orderBy, int limit) {
        var q = new Query();
        q.with(Sort.by(
                orderBy.equalsIgnoreCase("DESC")
                        ? Sort.Order.desc("precio")
                        : Sort.Order.asc("precio")));
        q.limit(limit);
        q.addCriteria(Criteria.where("categoria").is(category));

        return template.find(q, Product.class);
    }

    @Override
    public void updateStockByName(Product product, String name) {
        var q = new Query();
        q.addCriteria(Criteria.where("nombre").is(name));
        var update = new Update();
        update.set("stock", product.getStock());
        template.updateFirst(q, update, Product.class);
    }
}
