package com.codegym.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Trường này bắt buộc phải điền")
    private String name;

    private double price;

    private String description;

    private String imgUrl;

    @ManyToOne
    private Category category;
}
