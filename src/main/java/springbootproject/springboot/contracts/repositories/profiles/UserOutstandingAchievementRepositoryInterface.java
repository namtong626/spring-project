package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.UserOutstandingAchievement;
import springbootproject.springboot.response.achievementdto.AchievementResponseDTO;

public interface UserOutstandingAchievementRepositoryInterface extends JpaRepository<UserOutstandingAchievement, Long> {
    @Query("select new springbootproject.springboot.response.achievementdto.AchievementResponseDTO( ua.id, ua.description) \n"
            +
            "From UserOutstandingAchievement ua \n" +
            "where ua.profile.id =:profileId")
    List<AchievementResponseDTO> findAllByProfileId(Long profileId);
}
