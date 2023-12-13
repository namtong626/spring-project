package springbootproject.springboot.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String avatar;
    private String fullname;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String dob;
}
