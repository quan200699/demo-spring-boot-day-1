package com.codegym.demo.service.product;

import com.codegym.demo.model.Product;
import com.codegym.demo.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Page<Product> findAllProductByNameUsingQuery(String name, Pageable pageable);
}
