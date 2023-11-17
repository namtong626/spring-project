package springbootproject.springboot.requests;


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

    @NotEmpty(message = "Job name should not be empty")
    private String jobName;

    
    @NotEmpty(message = "Job decripttion should not be empty")
    private String jobDescription;

    @NotEmpty(message = "Date time should not be empty")
    private String dateTime;

    @Column(nullable = false)

    private String city;

   

    @NotEmpty(message = "Salary should not be empty")
    private String salary;

    @NotEmpty(message = "Company name should not be empty")
    private String compary;

   
    private String experience;

    private String certificate;

    @NotEmpty(message = "Address field should not be empty")
    private Address address;
   
    
    @Min(value=0, message="0: regardless of gender, 1: male, 2: female")
    private Integer sex;
}
