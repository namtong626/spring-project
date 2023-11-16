package springbootproject.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import springbootproject.springboot.enums.Exchange;
import springbootproject.springboot.enums.PositionExpected;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_job_profiles")
public class UserJobProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PositionExpected position_expected;

    @Enumerated(EnumType.STRING)
    private Exchange exchange = Exchange.valueOf("VND");

    private Double salary_from;

    private Double salary_to;

    @Column(nullable = false, columnDefinition="TINYINT COMMENT '1: FullTime, 2: PartTime, 3: Freelance, 4: Intern'")
    private Integer working_form;

    @Column(nullable = false, columnDefinition="TINYINT DEFAULT '0' COMMENT '0: None; 1: Remote'")
    private Integer working_methods = 0;

    @OneToOne(optional=false)
    @JoinColumn(name="country_id")
    private Country country;

    @OneToOne(optional=false)
    @JoinColumn(name="city_id")
    private City city;

    @OneToOne(optional=false)
    @JoinColumn(name="district_id")
    private District district;

    @OneToOne(optional=false)
    @JoinColumn(name="profile_id")
    private Profile profile;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="profiles_categories",
            joinColumns = @JoinColumn(name = "my_job_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "job_category_id", referencedColumnName = "id")
    )

    private List<JobCategory> jobCategory = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="profiles_welfares",
            joinColumns = @JoinColumn(name = "my_job_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "welfare_id", referencedColumnName = "id")
    )

    private List<Welfare> welfare = new ArrayList<>();

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @Generated(value = GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp ON UPDATE current_timestamp")
    @Generated(value = GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;
}
