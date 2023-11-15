package com.example.web.Services;

import java.util.Arrays;
import java.util.List;

import javax.validation.OverridesAttribute;

import org.springframework.stereotype.Service;
import com.example.web.Models.Role;
import com.example.web.Models.User;
import com.example.web.RepositoriesInterface.RoleRepositoryInterface;
import com.example.web.RepositoriesInterface.UserRepositoryInterface;
import com.example.web.Requests.UpdateUserDTO;
import com.example.web.Requests.UserDTO;

@Service
//Lỗi gì vậy
public class UserManagement implements UserManagementInterface {
    private RoleRepositoryInterface roleRepo;
    private UserRepositoryInterface userRepo;
    public UserManagement(UserRepositoryInterface userRepo, RoleRepositoryInterface roleRepo){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setIDcard(userDTO.getIdcard());
        // Cấp role_name
        Role role = new Role();
        String role_name;
        if(userDTO.getPassword().startsWith("ad")){
            role_name = "ADMIN";
        }
        else {
            role_name = "USER";
        }
        this.roleRepo.save(role);
// chưa hiểu dòng này 
        user.setRoles(Arrays.asList(role));
        this.userRepo.save(user);
    }
    // Check email exists
    @Override
    public boolean checkExistEmail(String email) {
        User user = this.userRepo.findUserByEmail(email);
        if(user != null) {return true;}
        else {return false;}
    }
//Dùng để làm gì 
    @Override
    public User findUserByEmail(String email) {
        return this.userRepo.findUserByEmail(email);
    }
    //update user
    @Override
    public boolean updateUser(String email, UpdateUserDTO updateUserDTO) {
        User user = this.userRepo.findUserByEmail(email);
        if(updateUserDTO.getNewPassword().equals(updateUserDTO.getConfirmPassword())){
            user.setPassword(updateUserDTO.getNewPassword());
            user.setEmail(updateUserDTO.getNewEmail());
            user.setUsername(updateUserDTO.getNewUsername());
            return true;
        }
        else {
            return false;
        }
    }
    //delete user 
    public boolean deleteUser(Long id) {
        User user = this.userRepo.getById(id);
        if(user != null) {
            this.userRepo.delete(user);
            return true;
        }
        return false;
    }
    
    
}
