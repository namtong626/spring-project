package springbootproject.springboot.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springbootproject.springboot.contracts.repositories.UserRepositoryInterface;
import springbootproject.springboot.models.Role;
import springbootproject.springboot.models.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    protected UserRepositoryInterface userRepo;

    public CustomUserDetailsService(
        UserRepositoryInterface userRepository
    ) {
        this.userRepo = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = this.userRepo.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                user.getEmail(), 
                user.getPassword(), 
                mapRolesAuthorities(user.getRoles())
            );
        } else {
            throw new UsernameNotFoundException("Invalid user or password");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesAuthorities (Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream().map(
            role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

        return mapRoles;
    }
    
}
