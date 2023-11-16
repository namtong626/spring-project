package springbootproject.springboot.requests;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;

    @NotEmpty(message = "First name should not be empty")
    private String firstname;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    
    @NotEmpty(message = "Confirm password should not be empty")
    private String confirm_password;

    @AssertTrue(message = "Password must match")
    public boolean isPasswordMatch()  {
        return password != null && password.equals(confirm_password);
    }
}
