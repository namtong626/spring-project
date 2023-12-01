package springbootproject.springboot.models;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
      
    @Column(nullable = false)
    private String title;
     
     @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String dateTime;

    
    @Column(nullable = false)
    private String postImage;

    @Column(nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryName", referencedColumnName = "categoryName")
    private String categoryName;
    
    
    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @Generated(value = GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp ON UPDATE current_timestamp")
    @Generated(value = GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;





}