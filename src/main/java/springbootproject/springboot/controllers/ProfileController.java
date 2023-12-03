package springbootproject.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import springbootproject.springboot.contracts.services.servicesinteface.ProfileService;
import springbootproject.springboot.response.ResponseDTO;
import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("get-list-profile-detail")
    @PermitAll
    public ResponseEntity<ResponseDTO> getProfileDetails(@Valid @RequestParam(name = "profileid") Long profileId) {
        ResponseDTO responseDTO = new ResponseDTO();
        // List<ProfileDetailsResponseDTO> data = ;
        responseDTO.setData(profileService.getProfileDetails(profileId));
        return ResponseEntity.ok().body(responseDTO);
    }
}
