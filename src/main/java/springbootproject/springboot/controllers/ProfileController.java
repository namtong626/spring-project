package springbootproject.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import springbootproject.springboot.contracts.services.servicesinteface.ProfileService;
import springbootproject.springboot.requests.profiledto.ProfileRequestDTO;
import springbootproject.springboot.response.ResponseDTO;
import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("get-list-profile-detail")
    public ResponseEntity<ResponseDTO> getProfileDetails(@Valid @RequestParam(name = "userId") Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(profileService.getProfileDetails(userId));
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("create-profile-data")
    public ResponseEntity<ResponseDTO> createProfile(@Validated @RequestBody ProfileRequestDTO profileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(profileService.createProfile(profileRequestDTO));
        return ResponseEntity.ok().body(responseDTO);
    }
}
