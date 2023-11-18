package springbootproject.springboot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name ="companys")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String web;

    @Column
    private String phone;

    @Column
    private String gmail;

    @Column
    private String location;

    @Column
    private String activity;

    @Column 
    private String description;

    @Column 
    private int follower;

    @Column
    private String jobs;
    
    @Column 
    private String account;

}
