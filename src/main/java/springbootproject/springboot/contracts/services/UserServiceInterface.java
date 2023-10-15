package springbootproject.springboot.contracts.services;

import springbootproject.springboot.requests.UserRequest;

public interface UserServiceInterface {
    void saveUser(UserRequest userRequest);
}
