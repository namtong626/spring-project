package springbootproject.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import springbootproject.springboot.enums.PositionExpected;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_experiences")

public class UserExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Vị trí / chức danh'")
    private String position;

    @Column(nullable = false)
    private String company;

    @Enumerated(EnumType.STRING)
    private PositionExpected position_expected;

    @Column(columnDefinition = "date")
    private Date start_date;

    @Column(columnDefinition = "date")
    private Date end_date;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @Generated(value = GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp ON UPDATE current_timestamp")
    @Generated(value = GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;

}
