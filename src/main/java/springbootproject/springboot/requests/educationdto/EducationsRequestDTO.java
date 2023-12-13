package springbootproject.springboot.requests.educationdto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EducationsRequestDTO {
    private String name;
    private String degree;
    private Date graduated_at;
    private String description;
}
