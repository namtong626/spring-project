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
import springbootproject.springboot.enums.TagType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="tags")
public class Tag {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    
    @Column
    private Long id;

    @Column (nullable = false)
    private String tag_name;

    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private TagType enities;

    // relationship with job
    @ManyToMany(mappedBy = "tags" )
    private List<Job> job;
}
