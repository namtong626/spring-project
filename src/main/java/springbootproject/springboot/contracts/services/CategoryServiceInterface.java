package main.java.springbootproject.springboot.contracts.services;

import java.util.List;

import springbootproject.springboot.models.Category;
import springbootproject.springboot.requests.CategoryRequest;

public interface CategoryServiceInterface {
    void savePost(CategoryRequest CategoryRequest);
    void deleteCategory(Long categoryId)
}
