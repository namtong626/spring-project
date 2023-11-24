package springbootproject.springboot.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;

     @NotEmpty(message = "this title should not be empty")
    private String title;


    @NotEmpty(message = "this slug cann't be empty")
    private String slug;

    
    @NotEmpty(message = "this image cann't be empty")
    private String postImage;

    @NotEmpty(message = "Date time should not be empty")
    private String dateTime;


    @NotEmpty(message = "content should not be empty")
    private String content;

   

   

  

   
  

   
}
