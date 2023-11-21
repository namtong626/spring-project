package springbootproject.springboot.models;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
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
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    
    @Column (name = "job_id")
    private Long id; // dùng làm khóa chính

    @Column (name = "description_job", nullable = false)
    private String description; // mô tả tính chất công việc - ex: làm những gì,...

    @Column (nullable = false)
    private String job_name; //tên công việc

    @Column (nullable = false)
    private String category; // mapp với category 

    //salary
    @Column (nullable = false)
    private double salary_from; 

    @Column (nullable = false)
    private double salary_to;


    @Column (nullable = false)
    private String inquiry; //yêu cầu, điều kiện cần có - ex: tốt nghiệp, thành thạo kỹ năng x
    
    @Column (nullable = false)
    private String welfare; //các chính sách phúc lợi cho công việc

    @Column (nullable = false)
    private String address; //địa chỉ làm việc - ex: các trụ sở làm việc 

    @Column (nullable = false)
    private String position; //chức vụ của công việc - ex: chuyên viên, admin

    @Column (nullable = false)
    private String formWork; //hình thức làm việc - ex: thực tập, online, offline

    @Column (nullable = false, name = "TimeUpdate" )
    private Date create_job; //thời gian bắt đầu đăng công việc

    @Column(nullable = false, name = "Timeline")
    private Date finalTime_employment; // thời gian kết thúc tuyển dụng công việc 

    //Mapping với company
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company; // tên công ty
   
    //Mapping với JobCategory
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private JobCategory job_category; //thể loại công việc

}
