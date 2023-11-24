package main.java.springbootproject.springboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import springbootproject.springboot.contracts.services.CategoryServiceInterface;
import springbootproject.springboot.models.Category;
import springbootproject.springboot.requests.CategoryRequest;

@Controller
public class CategoryController {
    
    protected CategoryService categoryService;

    public CategoryController(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/category/")
    public String index(Model model) {
        CategoryRequest category = new CategoryRequest();
        model.addAttribute("category", category);

        return "/category/show";
    }
    

    
  

     @PostMapping("/category/create")
    public ResponseEntity<String> createCategory(
        @Valid @ModelAttribute("category") CategoryRequest categoryRequest,
        BindingResult result,
        Model model
    ) {
       

        if (result.hasErrors()) {
            model.addAttribute("category", categoryRequest);
            return ResponseEntity.ok("error data");
		}

		this.categoryService.saveCategory(categoryRequest);

        return ResponseEntity.ok("create successfully");
    }


   

    @PostMapping("category/delete")
    public ResponseEntity<String> delete(
        @Valid @ModelAttribute("category") CategoryRequest categoryRequest,
        BindingResult result,
        Model model
    ) {
       

        if (result.hasErrors()) {
            model.addAttribute("category", categoryRequest);
            return ResponseEntity.ok("error data");
		}

		this.categoryService.deleteCategory(categoryRequest);

        return ResponseEntity.ok("delete successfully");
    }


}
