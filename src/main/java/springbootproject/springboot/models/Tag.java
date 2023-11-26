package springbootproject.springboot.models;

import java.util.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table (name="tags")
public class Tag {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    
    @Column (name = "tag_id")
    private Long id;

    @Column (nullable = false)
    private String tag_name;

    @Enumerated (EnumType.STRING)
    private Entity enity;

    // relationship with job
    @ManyToMany(mappedBy = "tags" )
    private List<Job> job;
}
