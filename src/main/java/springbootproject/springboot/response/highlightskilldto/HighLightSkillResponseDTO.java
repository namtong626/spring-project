package springbootproject.springboot.response.highlightskilldto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HighLightSkillResponseDTO {
    private Long id;
    private String description;
    private Integer level;
    private String skillExpertise;
}
