package springbootproject.springboot.contracts.services.servicesimplement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootproject.springboot.contracts.repositories.CityRepositoryInterface;
import springbootproject.springboot.contracts.repositories.DistrictRepositoryInterface;
import springbootproject.springboot.contracts.repositories.UserRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.JobCategoryRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.ProfileRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.UserCertificateRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.UserEducationRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.UserExperienceRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.UserHighlightSkillRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.UserLanguageRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.UserOutstandingAchievementRepositoryInterface;
import springbootproject.springboot.contracts.services.servicesinteface.ProfileService;
import springbootproject.springboot.enums.Exchange;
import springbootproject.springboot.enums.PositionExpected;
import springbootproject.springboot.models.Profile;
import springbootproject.springboot.models.User;
import springbootproject.springboot.models.UserCertificate;
import springbootproject.springboot.requests.profiledto.ProfileRequestDTO;
import springbootproject.springboot.response.jobcatedto.JobCategoryResponseDTO;
import springbootproject.springboot.response.profileresponse.DetailsResponseDTO;
import springbootproject.springboot.response.profileresponse.UserProfileDetailResponseDTO;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepositoryInterface profileRepo;

    @Autowired
    private UserRepositoryInterface userRepo;

    @Autowired
    private UserCertificateRepositoryInterface certiRepo;

    @Autowired
    private UserEducationRepositoryInterface eduRepo;

    @Autowired
    private UserHighlightSkillRepositoryInterface skillRepo;

    @Autowired
    private UserExperienceRepositoryInterface expRepo;

    @Autowired
    private UserOutstandingAchievementRepositoryInterface achieveRepo;

    @Autowired
    private UserLanguageRepositoryInterface languageRepo;

    @Autowired
    private JobCategoryRepositoryInterface jobRepo;

    @Autowired
    private DistrictRepositoryInterface districtRepo;

    @Autowired
    private CityRepositoryInterface cityRepo;

    @Override
    public UserProfileDetailResponseDTO getProfileDetails(Long userId) {

        List<Profile> resData = profileRepo.findAllProfilesByUserId(userId);
        List<DetailsResponseDTO> proDetail = new ArrayList<>();
        User user = userRepo.findById(userId).get();
        UserProfileDetailResponseDTO dto = UserProfileDetailResponseDTO.builder()
                .userId(userId)
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .profiles(proDetail)
                .build();

        for (Profile profile : resData) {

            DetailsResponseDTO pro = DetailsResponseDTO.builder()
                    .profileId(profile.getId())
                    .profileTitle(profile.getTitle())
                    .careerGoals(profile.getCareer_goals())
                    .exchange(profile.getExchange())
                    .positionExpected(profile.getPosition_expected())
                    .receiveJobAlert(profile.getReceive_job_alert())
                    .salaryFrom(profile.getSalary_from())
                    .salaryTo(profile.getSalary_to())
                    .showProfile(profile.getShow_profile())
                    .workingForm(profile.getWorking_form())
                    .workingMethods(profile.getWorking_methods())
                    .districtName(profile.getDistrict().getName())
                    .cityName(profile.getCity().getName())
                    .certies(certiRepo.findAllByProfileId(profile.getId()))
                    .educations(eduRepo.findAllByProfileId(profile.getId()))
                    .skills(skillRepo.findAllByProfileId(profile.getId()))
                    .experiences(expRepo.findAllByProfileId(profile.getId()))
                    .achievements(achieveRepo.findAllByProfileId(profile.getId()))
                    .languages(languageRepo.findAllByProfileId(profile.getId()))
                    // .jobcates(jobRepo.findALLByProfileId(profile.getId()))
                    .build();
            proDetail.add(pro);
        }
        return dto;
    }

    @Override
    public Boolean createProfile(ProfileRequestDTO profileRequestDTO) {
        Profile profile = Profile.builder()
                .title(profileRequestDTO.getTitle())
                .career_goals(profileRequestDTO.getCareer_goals())
                .created_at(LocalDateTime.now())
                .exchange(Exchange.valueOf(profileRequestDTO.getExchange()))
                .position_expected(PositionExpected.valueOf(profileRequestDTO.getPosition_expected()))
                .receive_job_alert(profileRequestDTO.getReceive_job_alert())
                .salary_from(profileRequestDTO.getSalary_from())
                .salary_to(profileRequestDTO.getSalary_to())
                .show_profile(profileRequestDTO.getShow_profile())
                .updated_at(null)
                .working_form(profileRequestDTO.getWorking_form())
                .working_methods(profileRequestDTO.getWorking_methods())
                .district(districtRepo.findById(profileRequestDTO.getDistrictId()).get())
                .city(cityRepo.findById(profileRequestDTO.getCityId()).get())
                .build();
        profile = profileRepo.save(profile);
        return false;
    }
}
