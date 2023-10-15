package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.springboot.models.Role;

public interface RoleRepositoryInterface extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
}
