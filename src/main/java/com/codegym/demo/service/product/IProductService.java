package com.codegym.demo.service.product;

import com.codegym.demo.model.Product;
import com.codegym.demo.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByNameContaining(String name);
    Iterable<Product> findAllProductByNameUsingQuery(String name);
}
