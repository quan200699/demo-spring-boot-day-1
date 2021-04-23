package com.codegym.demo.service.category;

import com.codegym.demo.exception.NotFoundException;
import com.codegym.demo.model.Category;
import com.codegym.demo.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    Category findCategoryById(Long id) throws NotFoundException;
}
