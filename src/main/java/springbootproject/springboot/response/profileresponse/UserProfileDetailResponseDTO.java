package springbootproject.springboot.response.profileresponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserProfileDetailResponseDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private List<DetailsResponseDTO> profiles;
}
