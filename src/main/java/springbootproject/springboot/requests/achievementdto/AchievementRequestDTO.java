package springbootproject.springboot.requests.achievementdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AchievementRequestDTO {
    private String descriptions;
}
