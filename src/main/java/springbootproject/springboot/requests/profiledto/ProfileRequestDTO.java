package springbootproject.springboot.requests.profiledto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootproject.springboot.requests.achievementdto.AchievementRequestDTO;
import springbootproject.springboot.requests.certificationdto.CertiesRequestDTO;
import springbootproject.springboot.requests.educationdto.EducationsRequestDTO;
import springbootproject.springboot.requests.experiencedto.ExperiencesRequestDTO;
import springbootproject.springboot.requests.languagedto.LanguageRequestDTO;
import springbootproject.springboot.requests.skilldto.SkillsRequestDTO;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileRequestDTO {
    private Long userId;
    private String title;
    private String career_goals;
    private String exchange;
    private String position_expected;
    private Integer receive_job_alert;
    private Double salary_from;
    private Double salary_to;
    private Integer show_profile;
    private Integer working_form;
    private Integer working_methods;
    private Long districtId;
    private Long cityId;
    private List<CertiesRequestDTO> certiesReq;
    private List<EducationsRequestDTO> eduReq;
    private List<ExperiencesRequestDTO> experReq;
    private List<SkillsRequestDTO> skillsReq;
    private List<AchievementRequestDTO> achieveReq;
    private List<LanguageRequestDTO> languageReq;
}
