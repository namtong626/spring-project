package springbootproject.springboot.response.profileresponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootproject.springboot.enums.Exchange;
import springbootproject.springboot.enums.PositionExpected;
import springbootproject.springboot.models.JobCategory;
import springbootproject.springboot.models.UserCertificate;
import springbootproject.springboot.response.achievementdto.AchievementResponseDTO;
import springbootproject.springboot.response.certificationresponse.CertiResponseDTO;
import springbootproject.springboot.response.educationdto.EducationResponseDTO;
import springbootproject.springboot.response.experiencedto.ExperienceResponseDTO;
import springbootproject.springboot.response.highlightskilldto.HighLightSkillResponseDTO;
import springbootproject.springboot.response.jobcatedto.JobCategoryResponseDTO;
import springbootproject.springboot.response.languagedto.LanguageResponseDTO;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DetailsResponseDTO {
    private Long profileId;
    private String profileTitle;
    private String careerGoals;
    private Exchange exchange;
    private PositionExpected positionExpected;
    private Integer receiveJobAlert;
    private Double salaryFrom;
    private Double salaryTo;
    private Integer showProfile;
    private Integer workingForm;
    private Integer workingMethods;
    private String districtName;
    private String cityName;
    List<CertiResponseDTO> certies;
    List<EducationResponseDTO> educations;
    List<HighLightSkillResponseDTO> skills;
    List<ExperienceResponseDTO> experiences;
    List<JobCategoryResponseDTO> jobcates;
    List<AchievementResponseDTO> achievements;
    List<LanguageResponseDTO> languages;

}
