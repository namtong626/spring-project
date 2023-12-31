package springbootproject.springboot.controllers;

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
public class RegisterController {

	protected UserServiceInterface userService;
	
	public RegisterController(UserServiceInterface userService) {
		this.userService = userService;
	}

    @GetMapping("/register")
    public String register(Model model) {
        UserRequest user = new UserRequest();
        model.addAttribute("user", user);

        return "authentication/register";
    }

    @PostMapping("/register/save")
    public String createAccount(
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
            return "authentication/register";
		}

		this.userService.saveUser(userRequest);

        return "redirect:/register?success";
    }
}
