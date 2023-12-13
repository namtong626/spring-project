package springbootproject.springboot.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import springbootproject.springboot.contracts.services.UserServiceInterface;
import springbootproject.springboot.models.User;
import springbootproject.springboot.requests.UserRequest;

@Controller
public class UserManagementController {
    
    protected UserServiceInterface userService;

    public UserManagementController(UserServiceInterface userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String index(Model model) {
        List<UserRequest> users = this.userService.getUsersDataList();
        model.addAttribute("users", users);
        return "pages/users";
    }

    
    @GetMapping("/users/create")
    public String create(Model model) {
        UserRequest user = new UserRequest();
        model.addAttribute("user", user);

        return "pages/forms/users/create_users";
    }

    @PostMapping("/users/save")
    public String save(
        @Valid @ModelAttribute("user") UserRequest userRequest,
        BindingResult result,
        Model model
    ) {
        User checkExistedEmail = this.userService.findByEmail(userRequest.getEmail());

        if (checkExistedEmail != null) {
            result.rejectValue("email", "409",  "This Email is already registed");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userRequest);
            return "pages/forms/users/create_users";
		}

		this.userService.saveUser(userRequest);

        return "redirect:/users?success";
    }

    
    @GetMapping("/users/edit/{id}")
    public String edit(Model model) {
        return "pages/update_users";
    }

    @PostMapping("/users/update")
    public String update(
        @Valid @ModelAttribute("user") UserRequest userRequest,
        BindingResult result,
        Model model
    ) {
        User checkExistedEmail = this.userService.findByEmail(userRequest.getEmail());

        if (checkExistedEmail != null) {
            result.rejectValue("email", "409",  "This Email is already registed");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userRequest);
            return "pages/update_users";
		}

		this.userService.saveUser(userRequest);

        return "redirect:/users?success";
    }

}
