package springbootproject.springboot.services;

import springbootproject.springboot.contracts.repositories.RoleRepositoryInterface;
import springbootproject.springboot.contracts.repositories.UserRepositoryInterface;
import springbootproject.springboot.contracts.services.UserServiceInterface;
import springbootproject.springboot.models.Role;
import springbootproject.springboot.models.User;
import springbootproject.springboot.requests.UserRequest;
import java.util.*;

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
        
        user.setName(userRequest.getName());
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
}
