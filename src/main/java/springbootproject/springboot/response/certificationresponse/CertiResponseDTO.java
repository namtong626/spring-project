package springbootproject.springboot.response.certificationresponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CertiResponseDTO {
    private Long userCertificateId;
    private Date certificateDueDate;
    private String issuedBy;
    private String certificateName;
    private Date certificateStartDate;
}
