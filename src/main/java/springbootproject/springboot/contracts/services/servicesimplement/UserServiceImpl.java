package springbootproject.springboot.contracts.services.servicesimplement;

import springbootproject.springboot.contracts.repositories.UserRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.ProfileRepositoryInterface;
import springbootproject.springboot.contracts.services.servicesinteface.UserServiceInterface;
import springbootproject.springboot.models.Profile;
import springbootproject.springboot.models.User;
import springbootproject.springboot.response.profileresponse.ProfileResponseDTO;
import springbootproject.springboot.response.userresponse.UserProfileResponseDTO;
import springbootproject.springboot.response.userresponse.UserResponseDTO;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepositoryInterface userRepo;

    @Autowired
    private ProfileRepositoryInterface profileRepo;

    @Override
    public List<UserResponseDTO> getUsersDataList(int pageNumber, Integer pageSize, String keyWord) {

        List<User> users = new ArrayList<>();
        if (keyWord == null || keyWord.isEmpty()) {
            users = userRepo.findUsers(pageNumber, pageSize);
        } else {
            users = userRepo.findUsersByKeyWord(pageNumber, pageSize, keyWord);
        }

        List<UserResponseDTO> listUsers = new ArrayList<>();
        for (User u : users) {
            UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                    .userId(u.getId())
                    .avatar(u.getAvatar())
                    .firstname(u.getFirstname())
                    .lastname(u.getLastname())
                    .address(u.getAddress())
                    .email(u.getEmail())
                    .dob(u.getDob())
                    .phone(u.getPhone())
                    .build();
            listUsers.add(userResponseDTO);
        }
        return listUsers;
    }

    @Override
    public UserProfileResponseDTO getUserProfiles(Long userId) {
        User user = this.userRepo.findById(userId).get();
        List<ProfileResponseDTO> profileResponses = new ArrayList<>();
        List<Profile> profiles = profileRepo.findAllProfilesByUserId(userId);
        for (Profile profile : profiles) {
            ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                    .profileId(profile.getId())
                    .title(profile.getTitle())
                    .build();
            profileResponses.add(responseDTO);
        }
        UserProfileResponseDTO userProfiles = UserProfileResponseDTO.builder()
                .userId(userId)
                .avatar(user.getAvatar())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dob(user.getDob())
                .address(user.getAddress())
                .listUserProfiles(profileResponses)
                .build();
        return userProfiles;
    }
}
