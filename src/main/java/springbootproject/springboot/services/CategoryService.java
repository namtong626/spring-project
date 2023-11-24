package main.java.springbootproject.springboot.services;

import springbootproject.springboot.contracts.repositories.CategoryRepositoryInterface;
import springbootproject.springboot.contracts.repositories.CategoryReponsitoryInterface;
import springbootproject.springboot.contracts.services.CategoryServiceInterface;
import springbootproject.springboot.models.Category;

import springbootproject.springboot.requests.CategoryRequest;
import java.util.*;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;



@Service
public class CategoryService implements CategoryServiceInterface {
    protected CategoryReponsitoryInterface categoryRepo;
   
   

    public CategoryService(
       
        CategoryReponsitoryInterface categoryRepo
        
    ) {
       
        this.categoryRepo = categoryRepo;
       
    }
    
    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = new category();
        category.setCategoryName(category.getCategoryName());

      
       


        this.categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.categoryRepo.deleteById(category);
        assertThat(this.categoryRepo.count()).isEqualTo(1);
    }


    

    @Override
    public List<CategoryRequest> getCategoryDataList() {
        List<Category> categories = this.categoryRepo.findAll();
        return categories.stream().map((category) -> convertUsers(category))
            .collect(Collectors.toList());
    }

    private CategoryRequest convertCategories(Category category) {
       CategoryRequest categoryRequest = new CategoryRequest();
        categories.setCategoryId(category.getCategoryId());
        categories.setCategoryName(category.getCategoryName());
        
       
      
        
        return categories;
    }

}
