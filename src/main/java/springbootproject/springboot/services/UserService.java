package springbootproject.springboot.services;

import springbootproject.springboot.contracts.repositories.RoleRepositoryInterface;
import springbootproject.springboot.contracts.repositories.UserRepositoryInterface;
import springbootproject.springboot.contracts.services.UserServiceInterface;
import springbootproject.springboot.helpers.Common;
import springbootproject.springboot.models.Role;
import springbootproject.springboot.models.User;
import springbootproject.springboot.requests.UserRequest;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    protected UserRepositoryInterface userRepo;
    protected RoleRepositoryInterface roleRepo;
    protected PasswordEncoder passwordEncoder;

    public UserService(
        UserRepositoryInterface userRepo,
        RoleRepositoryInterface roleRepo,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void saveUser(UserRequest userRequest) {
        User user = new User();
        
        user.setFirstname(userRequest.getFirstname());
        user.setEmail(userRequest.getEmail());

        String pw = this.passwordEncoder.encode(userRequest.getPassword());
        
        user.setPassword(pw);

        Role role = this.roleRepo.findByName("ROLE_ADMIN");
        
        if (role == null) {
            role = saveRole();
        }

        user.setRoles(Arrays.asList(role));

        this.userRepo.save(user);
    }

    private Role saveRole() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        
        return this.roleRepo.save(role);
    }

    @Override 
    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);

    }

    @Override
    public List<UserRequest> getUsersDataList() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map((user) -> convertUsers(user))
            .collect(Collectors.toList());
    }

    private UserRequest convertUsers(User user) {
        UserRequest users = new UserRequest();
        users.setId(user.getId());
        users.setFirstname(user.getFirstname());
        users.setEmail(user.getEmail());
        
        return users;
    }

    public List<User> getUserByRole(String roleName, String keyword, int offset, int limit) {
        return this.userRepo.findByConditions(roleName, keyword, offset, limit);
    }

    public int countUserByRole(String roleName, String keyword) {
        return this.userRepo.countByConditions(roleName, keyword);
    }
}
