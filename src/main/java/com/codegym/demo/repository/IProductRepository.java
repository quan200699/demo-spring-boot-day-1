package com.codegym.demo.repository;

import com.codegym.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findAllByNameContaining(String name);

    @Query(value = "SELECT * " +
            "FROM demo_product.products " +
            "WHERE name like ?1", nativeQuery = true)
    Iterable<Product> findAllProductByNameUsingQuery(String name);
}
