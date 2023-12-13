package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.UserHighlightSkill;
import springbootproject.springboot.response.highlightskilldto.HighLightSkillResponseDTO;

public interface UserHighlightSkillRepositoryInterface extends JpaRepository<UserHighlightSkill, Long> {
    @Query("select new springbootproject.springboot.response.highlightskilldto.HighLightSkillResponseDTO( us.id, us.description,us.level,us.skills_expertise) \n"
            +
            "From UserHighlightSkill us \n" +
            "where us.profile.id =:profileId")
    List<HighLightSkillResponseDTO> findAllByProfileId(Long profileId);
}
