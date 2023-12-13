package springbootproject.springboot.response.jobcatedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JobCategoryResponseDTO {
    private Long id;
    private String name;
}
