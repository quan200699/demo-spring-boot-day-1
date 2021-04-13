package com.codegym.demo.controller;

import com.codegym.demo.model.Product;
import com.codegym.demo.model.ProductForm;
import com.codegym.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Value(value = "${upload.path}")
    private String fileUpload;

    @GetMapping("/list")
    public ModelAndView showAll() {
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNewProduct(@ModelAttribute ProductForm productForm) {
        Product product = new Product();
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setImgUrl(fileName);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        ModelAndView modelAndView;
        if (productOptional.isPresent()) {
            modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", productOptional.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editProduct(@ModelAttribute Product product) {
        productService.save(product);
        return new ModelAndView("/product/edit");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        ModelAndView modelAndView;
        if (productOptional.isPresent()) {
            modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", productOptional.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute Product product) {
        productService.remove(product.getId());
        return "redirect:/products/list";
    }
}
