package springbootproject.springboot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="jobs")
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Long id;

    @Column
    private String description;

    @Column 
    private String title;

    @Column 
    private String category;

    @Column
    private String income;

    @Column
    private String accquirement;

    @Column
    private String welfare;

    @Column
    private String location;

    @Column
    private String position;

    @Column
    private String typeOfWork;

    @Column
    private String update;

    @Column
    private String finalDay;

    @Column
    private String company;

}
