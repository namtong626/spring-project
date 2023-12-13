package springbootproject.springboot.requests.languagedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LanguageRequestDTO {
    private String description;
    private String qualification;
    private Long countryId;
}
