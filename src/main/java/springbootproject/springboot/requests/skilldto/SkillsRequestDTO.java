package springbootproject.springboot.requests.skilldto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SkillsRequestDTO {
    private Integer level;
    private String description;
    private String skills_expertise;
}
