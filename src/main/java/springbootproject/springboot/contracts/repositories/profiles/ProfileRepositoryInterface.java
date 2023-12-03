package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.Profile;
import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

public interface ProfileRepositoryInterface extends JpaRepository<Profile, Long> {

    List<Profile> findAllProfilesByUserId(Long userId);

    // @Query(value = "\n" +
    // "select u.id as userId , concat(u.firstname,u.lastname) as fullName,\n" +
    // "p.id as profileId ,p.title, p.career_goals,p.exchange, p.position_expected,
    // p.receive_job_alert,p.salary_from,p.salary_to,p.show_profile,p.working_form,p.working_methods,\n"
    // +
    // "uc.id as userCertificateId, uc.due_date, uc.issued_by, uc.name as
    // certificateName, uc.start_date,\n" +
    // "ue.id as userEducationId, ue.degree, ue.description as eduDescription,
    // ue.graduated_at, ue.name as educationName,\n"
    // +
    // "uhs.id as skillId, uhs.description as skillDescription, uhs.level,
    // uhs.skills_expertise,\n" +
    // "uex.id as experienceId, uex.company, uex.description as expDescription,
    // uex.end_date, uex.position, uex.position_expected, uex.start_date,\n"
    // +
    // "uoa.id as achievementId, uoa.description as achievementDescription,\n" +
    // "ul.id as languageId, ul.description as langDescription, ul.qualification\n"
    // +
    // // "co.name as countryName,\n" +
    // // "c.name as cityName, \n" +
    // // "d.name as districtName\n" +
    // " from profiles as p left join users as u on u.id = p.user_id\n" +
    // "left join user_certificates as uc on uc.profile_id = p.id\n" +
    // "left join user_educations as ue on ue.profile_id = p.id\n" +
    // "left join user_highlight_skills as uhs on uhs.profile_id = p.id\n" +
    // "left join user_experiences as uex on uex.profile_id = p.id\n" +
    // "left join user_outstanding_achievements as uoa on uoa.profile_id = p.id\n" +
    // "left join user_languages as ul on ul.profile_id = p.id\n" +
    // "left join countries as co on ul.country_id = co.id\n" +
    // "left join cities as c on c.id = p.city_id\n" +
    // "left join districts as d on d.id = p.district_id\n" +
    // "where u.id = :profileId ", nativeQuery = true)
    @Query("select new springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO( u.id ,u.firstname,u.lastname ,\n" +
            "p.id  ,p.title, p.career_goals,p.exchange, p.position_expected, p.receive_job_alert,p.salary_from,p.salary_to,p.show_profile,p.working_form,p.working_methods,\n" +
            "uc.id , uc.due_date, uc.issued_by, uc.name , uc.start_date,\n" +
            "ue.id , ue.degree, ue.description , ue.graduated_at, ue.name,\n" +
            " uhs.id, uhs.description , uhs.level, uhs.skills_expertise,\n" +
            " uex.id , uex.company, uex.description , uex.end_date, uex.position, uex.position_expected, uex.start_date,\n" +
            "uoa.id , uoa.description ,\n" +
            "ul.id , ul.description , ul.qualification) \n"+
            "from Profile as p left join User as u on u.id = p.user.id\n" +
            "left join UserCertificate as uc on uc.profile.id = p.id\n" +
            "left join UserEducation as ue on ue.profile.id = p.id\n" +
            "left join UserHighlightSkill as uhs on uhs.profile.id = p.id\n" +
            "left join UserExperience as uex on uex.profile.id = p.id\n" +
            "left join UserOutstandingAchievement as uoa on uoa.profile.id = p.id\n" +
            "left join UserLanguage as ul on ul.profile.id = p.id \n" +
            "where u.id = :profileId")
    List<ProfileDetailsResponseDTO> findProfileDetailByProfileId(Long profileId);
}
