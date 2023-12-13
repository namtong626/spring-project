package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.Profile;
import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

public interface ProfileRepositoryInterface extends JpaRepository<Profile, Long> {

    List<Profile> findAllProfilesByUserId(Long userId);

    // @Query("select new
    // springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO(
    // u.id ,u.firstname,u.lastname ,\n" +
    // "p.id ,p.title, p.career_goals,p.exchange, p.position_expected,
    // p.receive_job_alert,p.salary_from,p.salary_to,p.show_profile,p.working_form,p.working_methods,\n"
    // +
    // "uc.id , uc.due_date, uc.issued_by, uc.name , uc.start_date,\n" +
    // "ue.id , ue.degree, ue.description , ue.graduated_at, ue.name,\n" +
    // " uhs.id, uhs.description , uhs.level, uhs.skills_expertise,\n" +
    // " uex.id , uex.company, uex.description , uex.end_date, uex.position,
    // uex.position_expected, uex.start_date,\n" +
    // "uoa.id , uoa.description ,\n" +
    // "ul.id , ul.description , ul.qualification) \n"+
    // "from Profile as p left join User as u on u.id = p.user.id\n" +
    // "left join UserCertificate as uc on uc.profile.id = p.id\n" +
    // "left join UserEducation as ue on ue.profile.id = p.id\n" +
    // "left join UserHighlightSkill as uhs on uhs.profile.id = p.id\n" +
    // "left join UserExperience as uex on uex.profile.id = p.id\n" +
    // "left join UserOutstandingAchievement as uoa on uoa.profile.id = p.id\n" +
    // "left join UserLanguage as ul on ul.profile.id = p.id \n" +
    // "where u.id = :profileId")
    // List<ProfileDetailsResponseDTO> findProfileDetailByProfileId(Long profileId);
}
