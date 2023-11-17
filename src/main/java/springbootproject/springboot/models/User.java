package springbootproject.springboot.models;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenerationTime;
import springbootproject.springboot.enums.Language;
import org.hibernate.annotations.Generated;
import springbootproject.springboot.enums.MaritalStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",
        indexes = {
        @Index(name = "email_index", columnList = "email"),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String avatar;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String phone;

    @Column(columnDefinition = "date")
    private Date dob;

    @Column(nullable = false)
    private String address;

    @OneToOne(optional=true)
    @JoinColumn(name="city_id")
    private City city;


    @OneToOne(optional=true)
    @JoinColumn(name="district_id")
    private District district;


    @Column(nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date email_verified_at;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MaritalStatus marital_status;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @Generated(value = GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp ON UPDATE current_timestamp")
    @Generated(value = GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Profile> profile;

}
