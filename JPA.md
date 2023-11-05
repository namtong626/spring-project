# Java Persistence API (JPA)

Nó được sử dụng để lưu giữ dữ liệu giữa đối tượng Java và cơ sở dữ liệu quan hệ (relationship).
JPA đóng vai trò là cầu nối giữa các mô hình hướng đối tượng và hệ thống cơ sở dữ liệu quan hệ.

JPA không tự thực hiện bất kỳ thao tác nào, nó đòi hỏi phải dc implementation.
Vì vậy, các công cụ ORM như Hibernate, TopLink và iBatis triển khai các thông số kỹ thuật JPA
để duy trì dữ liệu.

(Hibernate, TopLink và iBatis là thư viện ORM (Object Relational Mapping) mã nguồn mở giúp lập trình viên viết ứng dụng
Java có thể map các objects )

# JPA Object Relational Mapping

Object Relational Mapping (ORM)  là một chức năng được sử dụng để phát triển và duy trì mối quan hệ
giữa một đối tượng và cơ sở dữ liệu quan hệ bằng cách mapping trạng thái đối tượng vào cột cơ sở dữ liệu.
Nó có khả năng xử lý các hoạt động cơ sở dữ liệu khác nhau một cách dễ dàng như insert, update, delete, etc.

(OBJECT) -> [ORM] -> {DATABASE}

### ORM Frameworks

- Hibernate
- TopLink
- ORMLite
- iBATIS
- JPOX

### Mapping Directions

Mapping Directions được chia thành hai phần:

- Mối quan hệ một chiều
- Mối quan hệ hai chiều

### Types of Mapping

- One-to-one: Liên kết này được thể hiện bằng @OneToOne. Ở đây, thể hiện của mỗi entity có liên quan đến một thể hiện
  của entity khác.
- One-to-many: Liên kết này được thể hiện bằng @OneToMany. Trong mối quan hệ này, một thể hiện của một entity có thể
  liên quan đến nhiều thể hiện của entity khác.
- Many-to-one: Liên kết này được thể hiện bằng @ManyToOne.
- Many-to-many: Liên kết này được thể hiện bằng @ManyToMany

# JPA Entity

Nói chung, entity là một nhóm các trạng thái liên kết với nhau trong một đơn vị duy nhất.
Khi thêm hành vi, một entity hoạt động như một đối tượng và
trở thành thành phần chính của mô hình hướng đối tượng.
Vì vậy, một entity là một đối tượng do application-defined trong Java Persistence Library.

## Entity Properties

Đây là các thuộc tính của một Entity mà một đối tượng phải có:

- Tính bền vững(Persistability) Một đối tượng được gọi là persistent nếu nó được lưu trữ trong 
cơ sở dữ liệu và có thể được truy cập bất cứ lúc nào.
- Nhận dạng liên tục (Persistent Identity) Trong Java, mỗi entity là duy nhất và được biểu thị 
dưới dạng nhận dạng đối tượng. Tương tự, khi danh tính đối tượng được lưu trữ trong cơ sở dữ liệu 
thì nó được biểu diễn dưới dạng danh tính bền vững. Nhận dạng đối tượng này tương đương với khóa chính trong cơ sở dữ liệu.
- Tính giao dịch(Transactionality) Entity có thể thực hiện nhiều thao tác khác nhau như
tạo, xóa, cập nhật. Mỗi thao tác thực hiện một số thay đổi trong cơ sở dữ liệu. Nó đảm bảo rằng bất kỳ thay đổi nào được thực hiện trong cơ sở dữ liệu
  đều thành công hoặc thất bại.

### Vi dụ

Giả sử sử dụng một bảng tên là users trong database. Cấu trúc của bảng này như sau:
```angular2html
	
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create database test_db;

```

Object Relational Mapping là một kỹ thuật lập trình cho phép cho chúng ta thao tác với 
database thông qua các đối tượng của Java. Chúng ta sẽ ánh xạ các bảng, 
các cột của bảng trong database của chúng ta bằng các đối tượng và các thuộc tính của nó. 
Mối quan hệ giữa các bảng, các cột cũng được thể hiện rõ trong các đối tượng mà nó được ánh xạ.

Như ví dụ trên, chúng ta có thể ánh xạ bảng users bằng một đối tượng với tên là Users.
Cụ thể như sau:

```angular2html
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

Cột id trong bảng users sẽ được ánh xạ với thuộc tính id trong đối tượng Users, tương tự cho các cột khác.

Và bây giờ bạn có thể dùng đối tượng Users ở trên để thao tác với cơ sở dữ liệu mà không cần quan tâm loại cơ sở dữ liệu bạn đang dùng.


# Jakarta EE Persistence (https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.pdf)
Jakarta EE Persistence là một đặc tả, có nghĩa nó mô tả cách chúng ta truy cập, thao tác và quản lý dữ liệu giữa các đối tượng Java và các loại database.


## @Entity và @Table annotation trong JPA

### @Entity annotation
Công dụng chính của @Entity annotation là biến một JavaBean (class java thuần) trở thành một entity để chúng ta có thể thao tác với database sử dụng entity này. 
Nếu không sử dụng @Entity annotation thì khi chạy chương trình bạn sẽ gặp lỗi.

Nếu không sử dụng @Table annotation trong entity của mình, thì mặc định tên bảng trong database sẽ là tên lớp của entity.

```angular2html
@Entity
public class Lop {
 
    private Long id;
    private String name;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}
```

Trong trường hợp này, tên bảng trong database tương ứng sẽ là `lop`.

Ngoài ra chúng ta còn có thể điều chỉnh lại tên bảng bằng cách khai báo thêm thuộc tính name trong @Entity annotation. Trong trường hợp này, tên của entity cũng sẽ thay đổi theo.
```angular2html
@Entity(name = "lopjava")
public class Lop {
 
    private Long id;
    private String name;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}
```
Ở đây, tên bảng sẽ là lopjava và tên của entity này cũng sẽ thay đổi là LopJava.


### Annotation @Table

Sử dụng @Table annotation trong trường hợp, chỉ muốn thay đổi tên bảng của database sẽ sử dụng trong ứng dụng của mình 
mà không thay đổi tên của entity.

```angular2html
@Entity
@Table(name = "lopjava")
public class Lop {
 
    private Long id;
    private String name;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}
```


### Annotation @Id

Annotation này được khai báo để xác định đâu là thuộc tính primary key của entity tương ứng với các
cột primary key trong bảng. Ví dụ chúng ta lấy cột id làm primary key trong bảng thì khi 
mapping với Java entity object, chúng ta sẽ thêm @Id annotation đi kèm với thuộc tính tương ứng 
với cột id này.

```angular2html
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Table
@Getter
@Setter
public class Clazz {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column
    private String name;
```
Một entity bắt buộc phải có ít nhất một thuộc tính primary key đi kèm với @Id annotation. 
Nếu không khai báo bất kỳ một @Id annotation nào, khi chạy các bạn sẽ gặp lỗi.

Chúng ta có thể sử dụng nhiều kiểu dữ liệu của Java cho thuộc tính là primary key của entity


### annotation @GeneratedValue

Khi định nghĩa một primary key của một table trong database, chúng ta thường thêm chữ 
AUTO_INCREMENT. Với định nghĩa này, chúng ta không cần quan tâm đến giá trị của cột primary key 
mỗi khi chúng ta thêm một dữ liệu mới vào bảng, giá trị này sẽ được gán tự động và là duy nhất. 
Trong JPA, để làm được điều này, chúng ta phải khai báo thêm cho thuộc tính primary key của 
entity một annotation là @GeneratedValue. 

Trong JPA, để những cột primary key của một table trong database tự động được gán giá trị mỗi
khi chúng ta insert một dòng và giá trị này là duy nhất, chúng ta sẽ sử dụng annotation 
@GeneratedValue khi khai báo những cột primary key đó. 
Có 4 cách khác nhau để chúng ta làm được điều này, tuỳ thuộc 
vào giá trị của thuộc tính `strategy` mà chúng ta khai báo trong annotation @GeneratedValue.

- Auto Generation
```angular2html
    @Id
    @GeneratedValue
    private long studentId;
```

```angular2html
    @Id
    @GeneratedValue
    private UUID studentId;
```
Nếu ta không khai báo strategy thì mình sẽ sử dụng default generation để sinh ra giá trị cho khóa chính.
Thì trường khóa chính sẽ có giá trị là số (như là 1,2,3,4,5,6) hoặc UUID (dãy số và chữ kết hợp với nhau 8dd5f315-9788-4d00-87bb-10eed9eff566)
Nếu khóa chính là kiểu số thì Persisten sẽ tự động sinh giá trị cho khóa chính dựa trên cơ chế 
sequence number. Sequence number có nghĩa là giá trị sẽ tăng lên 1,2,3,4,5,6,7,8 …
Nếu khóa chính là kiểu UUID thì giá trị của khóa chính nó sẽ được sinh theo thuật toán UUID Generation

- IDENTITY Generation
```angular2html
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long studentId;
```
Khi sử dụng strategy Identity có nghĩa khóa chính sẽ tự động tăng lên cấp tiến như 1,2,3,4

- SEQUENCE Generation

Chúng ta sử dụng sequence-generator để có cấu hình cách tạo ra các giá trị cho khóa chính. 
  Như ví dụ trên ở Identify Generation. Thì thứ tự bắt đầu là 1 , sau đó tăng lên 2,3,4.
  Khi sử dụng sequence-generator ta hoàn toàn có thể thay đổi lại bước nhảy.
  Như ví dụ bên dưới, ta yêu cầu giá trị ban đầu là 4
(@Parameter(name = “initial_value”, value = “4”). Và ta muốn mỗi lần tăng là 2 đơn vị 
@Parameter(name = “increment_size”, value = “2”). Như vậy giá trị của userId sẽ là : 4,6,8,10
```angular2html
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "user_sequence"),
        @Parameter(name = "initial_value", value = "4"),
        @Parameter(name = "increment_size", value = "2")
        }
    )
    private long userId;
```
1. sequence_name : tên sequence
2. initial_value : giá trị ban đầu
3. increment_size : bước nhảy

- Table Generation

```angular2html
    @Entity
    @TableGenerator(name="person", initialValue=0, allocationSize=50)
    public class Persion {
      @GeneratedValue(strategy=GenerationType.Table, generator="person")
      @Id
      Long id;
    }
```
Table Generation Strategy cũng gần tương tự như sequence strategy. Nhưng trong phương pháp này 
ta sử dụng table để hỗ trợ việc tạo ra các giá trị cho trường khóa chính.
1. initialValue=0 Giá trị ban đầu được gán, trong trường hợp này id sẽ bằng 0.
2. allocationSize Giá trị sequence sẽ được sinh ra dựa trên khai báo allocationSize. 
Mặc định giá trị này là 50. Thì giá trị tăng 50 lần sau mỗi lần gọi. 
Nếu set là allocationSize = 1 thì nó sẽ tự động tăng dần đều. 
Sự khác nhau giữa SEQUENCE và Table Generator là giá trị initialValue.
Nếu là SEQUENCE nó sẽ lưu giá trị tiếp theo của dãy số vào table. 
Còn Table Generator sẽ lưu trữ giá trị cuối cùng đã được sử dụng vào table

### annotation @Column

Trong trường hợp tên column của table trong database mà các bạn đang làm việc không giống với tên 
của entity mà các bạn đang định nghĩa, các bạn có thể sử dụng annotation @Column để thay đổi tên này.
Ở đây, còn một số chức năng khác mà annotation này hỗ trợ cho chúng ta. 
```angular2html
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Table
@Getter
@Setter
public class LopHoc {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    //@Column
    private String name;
}
```
Trong ví dụ trên bởi vì các thuộc tính của entity LopHoc đều giống với tên của các column trong
bảng lophoc nên mình không cần sử dụng @Column annotation cũng được:


Nếu thuộc tính name của entity LopHoc là một giá trị khác, ví dụ đổi thành className, 
thì chúng ta cần phải khai báo để sử dụng annotation @Column như sau:

```angular2html
@Column(name = "name")
private String className;
```

Trong annotation @Column, ngoài thuộc tính name ra, chúng ta còn có thể khai báo các 
thuộc tính khác như sau (đa số các thuộc tính này dùng để định nghĩa cấu trúc của column, 
cần thiết khi chúng ta sử dụng JPA Tool để generate table từ entity) :
- columnDefinition 

Thuộc tính này cho phép các bạn sử dụng ngôn ngữ định dạng dữ liệu (DDL) để định nghĩa cấu trúc 
của một column. Sau đó, chúng ta có thể generate table từ entity sử dụng JPA Tool với định nghĩa này. 
Sử dụng thuộc tính này, các bạn có thể định nghĩa độ dài giá trị của cột (length), 
độ chính xác (precision) dành cho column với kiểu giá trị DECIMAL, khả năng mở rộng (scale) 
cũng dành cho column với kiểu giá trị DECIMAL, có được phép null hay không (nullable),
có tính duy nhất không (unique).

```angular2html
@Column(name = "name", columnDefinition = "VARCHAR(4) NOT NULL")
private String className;
```
- insertable
Thuộc tính này được sử dụng để cho phép sử dụng column này trong câu lệnh INSERT hay không? 
Mặc định giá trị của thuộc tính này là true, nếu các bạn định nghĩa thuộc tính này là false như sau:
```angular2html
@Column(name = "name", insertable = false)
private String className;
```

- length
- nullable
- precision (Thuộc tính này định nghĩa độ chính xác giá trị của column với kiểu giá trị DECIMAL. Nó cũng sẽ bị override nếu các bạn đã định nghĩa trong thuộc tính columnDefinition.)
- table (Định nghĩa table mà column này thuộc về.)
- unique
- updatable (Giống như thuộc tính insertable, thuộc tính này định nghĩa có cho phép sử dụng column này trong câu lệnh UPDATE hay không? Mặc định giá trị của thuộc tính này là true )

### annotation @ManyToOne
Annotation @ManyToOne trong JPA được dùng để thể hiện mối quan hệ nhiều – một giữa hai bảng trong một
database nào đó. Ở đó, nhiều record trong bảng A có mối quan hệ với một record trong bảng B.

```angular2html

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table
public class LopHoc {
 
    @Id
    @GeneratedValue
    private Integer id;
 
    @Column
    private String name;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
}
```

```angular2html

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table
public class HocVien {
 
    @Id
    @GeneratedValue
    private Integer id;
 
    @Column
    private String name;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    private LopHoc lopHoc;
    
    public LopHoc getLopHoc() {
        return lopHoc;
    }
    
    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }
}
```

Bởi vì, đây là mối quan hệ nhiều – một nhìn từ đối tượng sinh viên, do đó chúng ta sẽ đặt annotation @ManyToOne trong entity HocVien.

### annotation @OneToMany

Ta có sql create table:

```angular2html
CREATE TABLE `lophoc` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `hocvien` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `lophoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`lophoc_id`) REFERENCES `lophoc` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```

- entity Lophoc
```angular2html
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table
public class Lophoc {
   
  @Id
  @GeneratedValue
  private Integer id;
   
  @Column
  private String name;
   
  public Integer getId() {
     return id;
  }
   
  public void setId(Integer id) {
    this.id = id;
  }
   
  public String getName() {
    return name;
  }
   
  public void setName(String name) {
    this.name = name;
  }
 
}
```

- entity Hocvien
```angular2html

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table
public class Hocvien {
   
  @Id
  @GeneratedValue
  private Integer id;
   
  @Column
  private String name;
   
  @ManyToOne
  @JoinColumn(name = "lophoc_id")
  private Lophoc lophoc;
   
  public Lophoc getLophoc() {
    return lophoc;
  }
   
  public void seLophoc(Lophoc lophoc) {
    this.lophoc = lophoc;
  }
   
  public Integer getId() {
    return id;
  }
   
  public void setId(Integer id) {
    this.id = id;
  }
   
  public String getName() {
    return name;
  }
   
  public void setName(String name) {
    this.name = name;
  }
 
}
```

Bây giờ, chúng ta sẽ sử dụng annotation @OneToMany để thể hiện mối quan hệ của bảng lophoc với 
bảng họcvien trong JPA

Bởi vì, đây là mối quan hệ một – nhiều nhìn từ đối tượng Lophoc nên mình sẽ đặt annotation @OneToMany
trong đối tượng Lophoc, cụ thể như sau:

```angular2html
@OneToMany(mappedBy = "lophoc")
private Collection<Hocvien> hocvien;
 
public Collection<Hocvien> getHocvien() {
    return students;
}
 
public void setStudents(Collection<Hocvien> hocvien) {
    this.hocvien = hocvien;
}
```

Như các bạn thấy, trong annotation này, mình đã khai báo một thuộc tính là mappedBy 
với giá trị là lophoc. Giá trị “lophoc” ở đây là tên biến được định nghĩa với 
annotation @ManyToOne trong entity Hocvien.


###  @OneToOne annotation

Trong JPA, để thể hiện mối quan hệ một cái này tương ứng với một cái kia, 
ví dụ như một gia sư chỉ dạy 1 học viên duy nhất trong 1 ca chúng ta sẽ sử dụng annotation @OneToOne.
```angular2html
CREATE TABLE `schedules` (
  `id` int(11) NOT NULL,
  `hocvien_id` int(11) NOT NULL,
  `time` DATE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `tutors` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

Entity cho những bảng trên có sử dụng annotation @OneToOne sẽ như sau:
```angular2html
import javax.persistence.*;

@Entity
@Table
public class Tutor {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
```

```angular2html
import javax.persistence.*;

@Table
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer hocvien_id;

    @Column
    private Date time;

    @OneToOne(mappedBy = "schedule")
    private Tutor tutor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHocvienid() {
        return họcVienId;
    }

    public void setHocvienid(String hocvienid) {
        this.hocvienid = hocvienid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Tourist getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
```


### @ManyToMany annotation
