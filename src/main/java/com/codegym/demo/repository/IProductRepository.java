package com.codegym.demo.repository;

import com.codegym.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM demo_product.products " +
            "WHERE name like ?1", nativeQuery = true)
    Page<Product> findAllProductByNameUsingQuery(String name, Pageable pageable);
}
