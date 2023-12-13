package springbootproject.springboot.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataTableDTO {
    private List<UserDTO> data;
    private int recordsTotal;
    private int recordsFiltered;
}

