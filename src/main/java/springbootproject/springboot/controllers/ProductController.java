package springbootproject.springboot.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    
    @GetMapping("/products")
    public String index(Model model) {
        // System.out.println()
        model.addAttribute("message", "Hello from the controller");

        System.out.println("11111");
        // return "resultPage";
        return "pages/users1";

        // return "pages/products";
    }
    

}
