package springbootproject.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import springbootproject.springboot.enums.Exchange;
import springbootproject.springboot.enums.PositionExpected;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "profiles")

public class Profile {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'title profile'")
        private String title;

        @Column(nullable = false, length = 512)
        private String career_goals;

        @Column(nullable = false, columnDefinition = "TINYINT DEFAULT '0' COMMENT '0: Off; 1: On'")
        private Integer receive_job_alert;

        @Column(nullable = false, columnDefinition = "TINYINT DEFAULT '0' COMMENT '0: Off; 1: On'")
        private Integer show_profile;

        @Enumerated(EnumType.STRING)
        private PositionExpected position_expected;

        @Enumerated(EnumType.STRING)
        private Exchange exchange = Exchange.valueOf("VND");

        private Double salary_from;

        private Double salary_to;

        @Column(nullable = false, columnDefinition = "TINYINT COMMENT '1: FullTime, 2: PartTime, 3: Freelance, 4: Intern'")
        private Integer working_form;

        @Column(nullable = false, columnDefinition = "TINYINT DEFAULT '0' COMMENT '0: None; 1: Remote'")
        private Integer working_methods = 0;

        @OneToOne(optional = true)
        @JoinColumn(name = "country_id")
        private Country country;

        @OneToOne(optional = true)
        @JoinColumn(name = "city_id")
        private City city;

        @OneToOne(optional = true)
        @JoinColumn(name = "district_id")
        private District district;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name = "profiles_categories", joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "job_category_id", referencedColumnName = "id"))

        private List<JobCategory> jobCategory = new ArrayList<>();

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name = "profiles_welfares", joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "welfare_id", referencedColumnName = "id"))

        private List<Welfare> welfare = new ArrayList<>();

        @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Collection<UserExperience> userExperience;

        @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Collection<UserEducation> userEducation;

        @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Collection<UserCertificate> userCertificate;

        @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Collection<UserLanguage> userLanguage;

        @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Collection<UserHighlightSkill> userHighlightSkill;

        @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Collection<UserOutstandingAchievement> userOutstandingAchievement;

        @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
        @Generated(value = GenerationTime.INSERT)
        @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime created_at;

        @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp ON UPDATE current_timestamp")
        @Generated(value = GenerationTime.ALWAYS)
        @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime updated_at;
}
