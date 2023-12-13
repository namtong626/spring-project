package springbootproject.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import springbootproject.springboot.contracts.services.UserServiceInterface;
import springbootproject.springboot.dto.UserDTO;
import springbootproject.springboot.dto.UserDataTableDTO;
import springbootproject.springboot.models.User;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CustomerController {

    protected UserServiceInterface userService;

    public CustomerController(UserServiceInterface userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/admin/customers")
    public String index() {
        return "pages/customers/index";
    }

    @PostMapping("/admin/customers/data")
    @ResponseBody
    public UserDataTableDTO getListData(
            @RequestParam MultiValueMap<String, String> params) {
        int start = Integer.parseInt(params.getFirst("start"));
        int length = Integer.parseInt(params.getFirst("length"));
        String searchValue = params.getFirst("search") != "" ? params.getFirst("search") : null;

        List<User> users_query = this.userService.getUserByRole("ROLE_END_USER", searchValue, start, length);
        int totalCount = this.userService.countUserByRole("ROLE_END_USER", searchValue);

        List<UserDTO> users = users_query.stream().map(this::convertUsers)
                .toList();

        UserDataTableDTO result = new UserDataTableDTO();

        result.setData(users);
        result.setRecordsTotal(totalCount);
        result.setRecordsFiltered(totalCount);

        return result;
    }

    @GetMapping("/admin/customers/{user_id}")
    public String view(Model model) {
        return "pages/customers/detail";
        // load profile here
    }

    private UserDTO convertUsers(User user) {
        UserDTO users = new UserDTO();

        SimpleDateFormat outputFormat = new SimpleDateFormat("d/M/yyyy");

        String formattedDob = outputFormat.format(user.getDob());

        users.setId(user.getId());
        users.setAvatar(user.getAvatar());
        users.setFullname(user.getFirstname() + " " + user.getLastname());
        users.setEmail(user.getEmail());
        users.setPhone(user.getPhone());
        users.setDob(formattedDob);

        return users;
    }
}
