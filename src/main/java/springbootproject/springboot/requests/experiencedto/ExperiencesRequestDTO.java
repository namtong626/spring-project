package springbootproject.springboot.requests.experiencedto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExperiencesRequestDTO {
    private String comapany;
    private String description;
    private Date start_date;
    private Date end_date;
    private String position;
    private String position_expected;
}
