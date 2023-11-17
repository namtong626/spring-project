package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.UserOutstandingAchievement;

public interface UserOutstandingAchievementRepositoryInterface extends JpaRepository<UserOutstandingAchievement, Long>{
}
