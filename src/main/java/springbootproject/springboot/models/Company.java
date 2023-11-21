package springbootproject.springboot.models;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "companys")
public class Company {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)

       @Column (name = "company_id") //làm khóa chính 
       private Long id; 

       @Column (nullable = true)
       private String activity; // chứa info lĩnh vực kinh doanh của công ty - ex: xuất nhập khẩu, technology

       @Column (nullable = true)
       private String description; // chứa mô tả về công kinh: năm thành lập, triết lý kinh doanh, slogan

       @Column (nullable = true)
       private int follower; // chứa số lượng ứng viên tiềm năng theo dõi của công ty

       @Column (nullable = false)
       private String email; // chứa địa chỉ gmail của công ty

       @Column (nullable = false)
       private String location; // trụ sở công ty

       @Column (nullable = false, length = 10)
       private int phone;  // chứa số điện thoại đại diện của công ty

       @Column (nullable = false)
       private String URL; // chứa địa chỉ website của công ty

        @OneToMany(fetch = FetchType.EAGER)
        private List<Job> jobs;
    }
