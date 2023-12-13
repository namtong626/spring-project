package springbootproject.springboot.response.achievementdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AchievementResponseDTO {
    private Long id;
    private String description;
}
