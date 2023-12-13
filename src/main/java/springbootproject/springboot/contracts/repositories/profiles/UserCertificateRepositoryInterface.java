package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.UserCertificate;
import springbootproject.springboot.response.certificationresponse.CertiResponseDTO;

public interface UserCertificateRepositoryInterface extends JpaRepository<UserCertificate, Long> {
    @Query("select new springbootproject.springboot.response.certificationresponse.CertiResponseDTO( uc.id, uc.due_date,uc.name,uc.issued_by, uc.start_date) \n" +
            "From UserCertificate uc \n" +
            "where uc.profile.id =:profileId" )
    List<CertiResponseDTO> findAllByProfileId(Long profileId);

}
