package springbootproject.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springbootproject.springboot.contracts.services.UserServiceInterface;
import springbootproject.springboot.helpers.Common;
import springbootproject.springboot.models.User;

import java.util.List;

@Controller
public class CustomerController {

    protected UserServiceInterface userService;

    public CustomerController(UserServiceInterface userService) {
        this.userService = userService;
    }


    @GetMapping("/customers")
    public String index() {
        List<User> users = this.userService.getUserByRole("ROLE_END_USER", "", 10);

        return "pages/users";
    }
}
