package springbootproject.springboot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springbootproject.springboot.contracts.services.UserServiceInterface;
import springbootproject.springboot.dto.UserDTO;
import springbootproject.springboot.helpers.Common;
import springbootproject.springboot.models.User;

import java.util.List;

@Controller
public class CustomerController {

    protected UserServiceInterface userService;

    public CustomerController(UserServiceInterface userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/admin/customers")
    public String index(Model model) {
        List<UserDTO> users = this.userService.getUserByRole("ROLE_END_USER", "", 10)
                .stream().map(this::convertUsers)
                .toList();
        model.addAttribute("users", users);

        return "pages/customers/index";
    }

    private UserDTO convertUsers(User user) {
        UserDTO users = new UserDTO();
        users.setId(user.getId());
        users.setFirstname(user.getFirstname());
        users.setEmail(user.getEmail());

        return users;
    }
}
