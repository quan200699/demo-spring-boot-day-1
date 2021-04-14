package com.codegym.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private Long id;

    @NotEmpty(message = "Trường này bắt buộc phải điền")
    private String name;

    private double price;

    private String description;

    private MultipartFile image;

    private Category category;
}
