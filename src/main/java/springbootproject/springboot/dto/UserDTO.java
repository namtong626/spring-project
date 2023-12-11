package springbootproject.springboot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String avatar;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private Date dob;
}
