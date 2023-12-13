package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.UserExperience;
import springbootproject.springboot.response.experiencedto.ExperienceResponseDTO;

public interface UserExperienceRepositoryInterface extends JpaRepository<UserExperience, Long> {
    @Query("select new springbootproject.springboot.response.experiencedto.ExperienceResponseDTO( uex.id, uex.company,uex.start_date,uex.end_date, uex.position, uex.position_expected, uex.description) \n"
            +
            "From UserExperience uex \n" +
            "where uex.profile.id =:profileId")
    List<ExperienceResponseDTO> findAllByProfileId(Long profileId);
}
