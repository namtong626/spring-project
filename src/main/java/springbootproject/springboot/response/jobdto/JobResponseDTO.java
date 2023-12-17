package springbootproject.springboot.response.jobdto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDTO {
    private Long id;
    private String jobName; //mapping title in Job models 
    private Date startDate;
    private Date endDate;
    private double salary; //mapping salary_from in Job models
    private String companyName; // mapping company in Job models
}
