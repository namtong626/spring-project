package springbootproject.springboot.response.languagedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootproject.springboot.enums.LanguageQualification;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LanguageResponseDTO {
    private Long id;
    private LanguageQualification qualification;
    private String description;
}
