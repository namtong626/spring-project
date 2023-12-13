package springbootproject.springboot.response.userresponse;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootproject.springboot.response.profileresponse.ProfileResponseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfileResponseDTO {
    private Long userId;
    private String avatar;
    private String firstname;
    private String lastname;
    private String phone;
    private Date dob;
    private String address;
    private String email;
    private List<ProfileResponseDTO> listUserProfiles;
}
