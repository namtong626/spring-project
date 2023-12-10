package springbootproject.springboot.reponse;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobReponse {
    private Long id;
    private String title;
    private Date start_date;
    private Date end_date;
    private String companyname;
}
