package springbootproject.springboot.contracts.services.servicesinteface;

import java.util.List;

import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

// import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

public interface ProfileService {
    Object getProfileDetails(Long profileId);
}
