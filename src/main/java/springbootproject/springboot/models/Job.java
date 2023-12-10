package springbootproject.springboot.models;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import jakarta.persistence.CascadeType;
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

    @Column
    private Long id; 

    //Mapping với company
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company; // tên công ty

    //Mapping với JobCategory
    @OneToMany(fetch = FetchType.EAGER)
    private List<JobCategory> job_category_id;

    @Column(nullable = false)
    private String title; // tên công việc

    @Column(nullable = false)
    private String description; //chi tiết công việc

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date end_date;

    @Column (columnDefinition="TINYINT COMMENT '0: NotRequirement, 2: Male, 3: female'", nullable = false)
    private int gender; 

    @Column (columnDefinition = "TINYINT COMMENT '0: None, 1: Remote'", nullable = false)
    private int working_from;

    @Column (columnDefinition = "TINYINT COMMENT '0: NotRequirement, 2: At least 1 year, 3: At least 2 years, 4: At least 3 years, 5: At least 4 years, 6: At least 5 years, 7: At least 6 years, 8: At least 7 years, 9: At least 8 years, 10: At least 9 years, 11: At least 10 years'", nullable = false)
    private int experience;

    @Column (columnDefinition = "TINYINT COMMENT '0: following Range, 1: following negotiation'", nullable = false)
    private int salary_type;

    @Column (nullable = true)
    private double salary_from; //mức lương dao động từ

    @Column (nullable = true)
    private double salary_to; //mức lương dao động đến

    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private Exchange exchange;

    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private PositionExpected poistion;

    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private Degree degree;

    @Column(nullable = false)
    private String address;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @Generated(value = GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp ON UPDATE current_timestamp")
    @Generated(value = GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;
    
    // relationship with Tag
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable (
        name ="jobs_tags",
        joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> tags = new ArrayList<>();

    // relationship with City
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "jobs_cities",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"))
    private List<City> cities = new ArrayList<>();
}
