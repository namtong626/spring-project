package springbootproject.springboot.contracts.services;

import springbootproject.springboot.models.User;
import springbootproject.springboot.requests.UserRequest;

public interface UserServiceInterface {
    void saveUser(UserRequest userRequest);
    
    User findByEmail(String email);
}
