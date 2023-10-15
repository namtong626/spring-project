package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.springboot.models.User;
import springbootproject.springboot.requests.UserRequest;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
}
