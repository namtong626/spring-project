package springbootproject.springboot.response.userresponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private Long userId;
    private String avatar;
    private String firstname;
    private String lastname;
    private String phone;
    private Date dob;
    private String address;
    private String email;
}
