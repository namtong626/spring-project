package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.UserEducation;
import springbootproject.springboot.response.educationdto.EducationResponseDTO;

public interface UserEducationRepositoryInterface extends JpaRepository<UserEducation, Long> {
        @Query("select new springbootproject.springboot.response.educationdto.EducationResponseDTO( ud.id, ud.name,ud.graduated_at,ud.degree, ud.description) \n"
                        +
                        "From UserEducation ud \n" +
                        "where ud.profile.id =:profileId")
        List<EducationResponseDTO> findAllByProfileId(Long profileId);
}
