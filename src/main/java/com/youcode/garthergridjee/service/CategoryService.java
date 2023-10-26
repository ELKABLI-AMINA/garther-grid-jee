package com.youcode.garthergridjee.service;


import com.youcode.garthergridjee.entities.Category;
import com.youcode.garthergridjee.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository = new CategoryRepository();
    }

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category) {
        if(category.getName()==null || category.getDescription()==null){
            throw  new IllegalArgumentException("can not save this cate");
        }
        categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAll();
    }

    public void updateCategory(Category category) {
        if(category.getName()==null || category.getDescription()==null){
            throw  new IllegalArgumentException("can not update this cate");
        }
        categoryRepository.update(category);
    }

    public void deleteCategory(Long id) {
        Category repositoryById = categoryRepository.getById(id);
        if(repositoryById==null) {
            throw new IllegalArgumentException("can not delete category");
        }else {
            categoryRepository.delete(id);
        }
    }
}
