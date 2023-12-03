package springbootproject.springboot.response.profileresponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootproject.springboot.enums.Degree;
import springbootproject.springboot.enums.Exchange;
import springbootproject.springboot.enums.LanguageQualification;
import springbootproject.springboot.enums.PositionExpected;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileDetailsResponseDTO {
    private Long userId;
    private String firstName;
    private String lastName;
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
    private Long userCertificateId;
    private Date certificateDueDate;
    private String issuedBy;
    private String certificateName;
    private Date certificateStartDate;
    private Long userEducationId;
    private Degree degree;
    private String educationName;
    private Date graduatedAt;
    private String eduDescription;
    private Long skillId;
    private String skillExpertise;
    private Integer level;
    private String skillDescription;
    private Long experienceId;
    private String company;
    private String expDescription;
    private Date expStartDate;
    private String position;
    private PositionExpected expPositionExpected;
    private Date expEndDate;
    private Long achievementId;
    private String achievementDescription;
    private Long languageId;
    private String langDescription;
    private LanguageQualification langQualification;
    // private String countryName;
    // private String cityName;
    // private String districtName;
}
