package springbootproject.springboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
public class UserController {
    
    protected UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }


    @GetMapping("/users1")
    public String index(Model model) {
        UserRequest user = new UserRequest();
        model.addAttribute("user", user);

        return "pages/users1";
    }
    

    
    @PostMapping("/users1/list-data")
    public String getUsersDataList(Model model) {
        List<UserRequest> users = this.userService.getUsersDataList();
        model.addAttribute("users", users);

        return "pages/user_table";
    }

     @PostMapping("/users1/save")
    public ResponseEntity<String> save(
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
            return ResponseEntity.ok("error data");
		}

		this.userService.saveUser(userRequest);

        return ResponseEntity.ok("create successfully");
    }


}
