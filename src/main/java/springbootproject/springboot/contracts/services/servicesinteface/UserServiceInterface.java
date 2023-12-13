package springbootproject.springboot.contracts.services.servicesinteface;

import java.util.List;

import springbootproject.springboot.response.userresponse.UserProfileResponseDTO;
import springbootproject.springboot.response.userresponse.UserResponseDTO;

public interface UserServiceInterface {

    List<UserResponseDTO> getUsersDataList(int pagenumber,Integer pageSize, String keyWord);

    UserProfileResponseDTO getUserProfiles(Long userId);
}
