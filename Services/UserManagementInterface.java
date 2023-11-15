package com.example.web.Services;

import java.util.List;

import com.example.web.Models.User;
import com.example.web.Requests.UpdateUserDTO;
import com.example.web.Requests.UserDTO;

public interface UserManagementInterface {
    //save user data
    void saveUser(UserDTO userDTO);
    boolean checkExistEmail(String email);
    User findUserByEmail(String email);
    //Read data 
    List<User> fetchUserList();
    //Update data
    boolean updateUser(String email, UpdateUserDTO updateUserDTO);
    //Delete data 
    boolean deleteUser(Long id);

}
