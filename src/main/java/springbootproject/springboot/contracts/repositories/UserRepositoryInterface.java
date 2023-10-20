package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.springboot.models.User;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
