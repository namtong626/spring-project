package springbootproject.springboot.requests.certificationdto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CertiesRequestDTO {
    private String name;
    private Date start_date;
    private Date due_date;
    private String issued_by;
}
