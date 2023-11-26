package springbootproject.springboot.models;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springbootproject.springboot.enums.Degree;
import springbootproject.springboot.enums.Exchange;
import springbootproject.springboot.enums.PositionExpected;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column (name = "job_id")
    private Long id; 

    //Mapping với company
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company; // tên công ty

    //Mapping với JobCategory
    @OneToMany(fetch = FetchType.EAGER)
    private List<JobCategory> job_category_id;

    @Column
    private String title; // tên công việc

    @Column
    private String discription; //chi tiết công việc

    @Column
    private Date start_date;

    @Column
    private Date end_date;

    @Column (columnDefinition="TINYINT COMMENT '0: NotRequirement, 2: Male, 3: female'")
    private int gender; 

    @Column (columnDefinition = "TINYINT COMMENT '0: None, 1: Remote'")
    private int working_from;

    @Column (columnDefinition = "TINYINT COMMENT '0: NotRequirement, 2: At least 1 year, 3: At least 2 years, 4: At least 3 years, 5: At least 4 years, 6: At least 5 years, 7: At least 6 years, 8: At least 7 years, 9: At least 8 years, 10: At least 9 years, 11: At least 10 years'")
    private int experience;

    @Column (columnDefinition = "TINYINT COMMENT '0: following Range, 1: following negotiation'")
    private int salary_type;

    @Column (nullable = true)
    private double salary_from; //mức lương dao động từ

    @Column (nullable = true)
    private double salary_to; //mức lương dao động đến

    @Enumerated (EnumType.STRING)
    private Exchange exchange;

    @Enumerated (EnumType.STRING)
    private PositionExpected poistion;

    @Enumerated (EnumType.STRING)
    private Degree degree;

    @Column 
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate updated_at;

    // relationship with Tag
    @ManyToMany
    @JoinTable (
        name ="jobs_tags",
        joinColumns = @JoinColumn(name = "id", referencedColumnName = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    // relationship with City
    @ManyToMany
    @JoinTable (
        name ="jobs_cities",
        joinColumns = @JoinColumn(name = "id", referencedColumnName = "job_id"),
        inverseJoinColumns = @JoinColumn(name ="id")
    )
    private List<City> city = new ArrayList<>();

    // relationship with country
    @ManyToMany
    @JoinTable (
        name = "jobs_countries",
        joinColumns = @JoinColumn(name = "id", referencedColumnName = "job_id"),
        inverseJoinColumns = @JoinColumn(name ="id")
    )
    private List<Country> country = new ArrayList<>();

    // relationship with district
    @ManyToMany
    @JoinTable (
        name = "jobs_districts",
        joinColumns = @JoinColumn(name = "id", referencedColumnName = "job_id"),
        inverseJoinColumns = @JoinColumn(name="id")
    )
    private List<District> district = new ArrayList<>();
}
