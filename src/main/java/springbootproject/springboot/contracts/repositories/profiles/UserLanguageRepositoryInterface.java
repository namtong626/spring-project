package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.UserLanguage;
import springbootproject.springboot.response.languagedto.LanguageResponseDTO;

public interface UserLanguageRepositoryInterface extends JpaRepository<UserLanguage, Long> {
    @Query("select new springbootproject.springboot.response.languagedto.LanguageResponseDTO( ul.id, ul.qualification,ul.description) \n"
            +
            "From UserLanguage ul \n" +
            "where ul.profile.id =:profileId")
    List<LanguageResponseDTO> findAllByProfileId(Long profileId);
}
