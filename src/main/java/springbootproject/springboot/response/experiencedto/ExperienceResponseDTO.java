package springbootproject.springboot.response.experiencedto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootproject.springboot.enums.PositionExpected;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExperienceResponseDTO {
    private Long id;
    private String company;
    private Date startDate;
    private Date endDate;
    private String position;
    private PositionExpected positionExpected;
    private String description;
}
