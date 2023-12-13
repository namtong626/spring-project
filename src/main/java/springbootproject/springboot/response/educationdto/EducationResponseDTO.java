package springbootproject.springboot.response.educationdto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootproject.springboot.enums.Degree;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EducationResponseDTO {
    private Long id;
    private String name;
    private Date graduatedAt;
    private Degree degree;
    private String description;
}
