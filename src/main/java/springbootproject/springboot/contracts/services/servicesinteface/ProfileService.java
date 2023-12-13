package springbootproject.springboot.contracts.services.servicesinteface;

import java.util.List;

import springbootproject.springboot.requests.profiledto.ProfileRequestDTO;
import springbootproject.springboot.response.profileresponse.UserProfileDetailResponseDTO;

// import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

public interface ProfileService {
    UserProfileDetailResponseDTO getProfileDetails(Long profileId);

    Boolean createProfile(ProfileRequestDTO profileRequestDTO);
}
