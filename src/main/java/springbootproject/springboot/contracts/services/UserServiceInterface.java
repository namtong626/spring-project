package springbootproject.springboot.contracts.services;

import java.util.List;

import springbootproject.springboot.models.User;
import springbootproject.springboot.requests.UserRequest;

public interface UserServiceInterface {
    void saveUser(UserRequest userRequest);
    
    User findByEmail(String email);

    List<UserRequest> getUsersDataList();
}
