package springbootproject.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<District> district;

    @OneToOne(optional = false, mappedBy = "city")
    public UserJobProfile userJobProfile;

    @OneToOne(optional = false, mappedBy = "city")
    public User user;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @Generated(value = GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp ON UPDATE current_timestamp")
    @Generated(value = GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;

    // relationship with job 
    @ManyToMany (mappedBy = "city", fetch = FetchType.EAGER)
    private List<Job> job;
}
