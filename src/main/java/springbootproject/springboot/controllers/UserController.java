package springbootproject.springboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import springbootproject.springboot.contracts.services.servicesinteface.UserServiceInterface;
import springbootproject.springboot.response.ResponseDTO;
import springbootproject.springboot.response.userresponse.UserResponseDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("get-list-user")
    @PermitAll
    public ResponseEntity<ResponseDTO> getUsersData(
            @Valid @RequestParam(name = "pagenumber") int pageNo,
            @Valid @RequestParam(name = "pagesize") Integer pageSize,
            @Valid @RequestParam(name = "keyword") String keyWord) {
        if (pageSize == null) {
            pageSize = 10;
        }
        int pageNumber = pageNo * 10;
        ResponseDTO responseDTO = new ResponseDTO();
        List<UserResponseDTO> usersData = userService.getUsersDataList(pageNumber, pageSize, keyWord);
        responseDTO.setData(usersData);
        responseDTO.setResult(usersData.size());
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("get-profiles-of-user")
    @PermitAll
    public ResponseEntity<ResponseDTO> getUserProfiles(@Valid @RequestParam(name = "userid") Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.getUserProfiles(userId));
        responseDTO.setResult(userService.getUserProfiles(userId).getListUserProfiles().size());
        return ResponseEntity.ok().body(responseDTO);
    }
}
