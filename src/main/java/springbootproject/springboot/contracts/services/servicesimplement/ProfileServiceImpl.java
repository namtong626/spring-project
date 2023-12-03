package springbootproject.springboot.contracts.services.servicesimplement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootproject.springboot.contracts.repositories.profiles.ProfileRepositoryInterface;
import springbootproject.springboot.contracts.services.servicesinteface.ProfileService;
import springbootproject.springboot.response.profileresponse.ProfileDetailsResponseDTO;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepositoryInterface profileRepo;

    @Override
    public Object getProfileDetails(Long profileId) {

        List<ProfileDetailsResponseDTO> resData = profileRepo.findProfileDetailByProfileId(profileId);
        List<Object> pro = new ArrayList<>();
        Object[] user = {};
        Object[] profiles = {};
        Object[] certificates = {};
        // Object[] educations = {};
        // Object[] skills = {};
        // Object[] experiences = {};
        // Object[] achievements = {};
        // Object[] languages = {};
        // Map<Long, Integer> map = new HashMap<>();
        HashSet<Long> map = new HashSet<Long>();
        Long tmp = 0L;
        for (int i = 0; i < resData.size(); i++) {
            user = new Object[] {
                    resData.get(i).getUserId(),
                    pro
            };

            if (!map.contains(tmp)) {
                map.add(resData.get(i).getProfileId());
                profiles = new Object[] {
                        resData.get(i).getProfileId(),
                        resData.get(i).getProfileTitle(),
                        resData.get(i).getCareerGoals(),
                        resData.get(i).getExchange(),
                        resData.get(i).getPositionExpected(),
                        resData.get(i).getReceiveJobAlert(),
                        resData.get(i).getSalaryFrom(),
                        resData.get(i).getSalaryTo(),
                        resData.get(i).getShowProfile(),
                        resData.get(i).getWorkingForm(),
                        resData.get(i).getWorkingMethods(),
                        certificates,
                        // educations,
                        // skills,
                        // experiences,
                        // achievements,
                        // languages,
                        // resData.get(i).getCityName(),
                        // resData.get(i).getDistrictName(),
                        // resData.get(i).getCountryName()
                };
                pro.add(profiles);
                tmp = resData.get(i).getProfileId();
                if (map.contains(tmp)) {
                    certificates = new Object[] {
                            resData.get(i).getUserCertificateId(),
                            resData.get(i).getCertificateDueDate(),
                            resData.get(i).getCertificateName(),
                            resData.get(i).getCertificateStartDate(),
                            resData.get(i).getIssuedBy()
                    };
                    map.add(resData.get(i).getProfileId());
                }
            }
            map.add(resData.get(i).getUserId());
        }
        return user;
    }

}
