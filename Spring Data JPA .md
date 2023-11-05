# Spring Data JPA
(https://spring.io/projects/spring-data-jpa/)

Spring Data JPA giúp chúng ta đơn giản hoá quá trình viết câu lệnh SQL để thao tác với database thông qua cơ chế tự động tạo câu lệnh SQL từ code Java.

## JPA Query Language
Vì mình sẽ sử dụng JPA với implementation của Hibernate, do đó, mình sẽ thêm Hibernate dependencies như sau:
<!-- - org.hibernate:hibernate-core
- org.hibernate:hibernate-entitymanager -->

Thông thường, khi cần thao tác đến một database bất kỳ, chúng ta phải viết câu SQL dựa trên thông tin
của các cột, các bảng trong database đó và có thể đối với mỗi loại database khác nhau, syntax của câu 
SQL cũng khác nhau. Để loại bỏ những nhược điểm này, các bạn có thể sử dụng JPA Query Language (JPQL).

Điều đầu tiên mình cần nói với các bạn về JPA Query Language đó là nó cho phép chúng ta định nghĩa
các câu query dựa trên các entity chứ không dựa vào tên các cột, các bảng trong database.

Cấu trúc và cú pháp của JPA Query Language thì rất tương tự như cấu trúc và cú pháp của câu SQL.
Ví dụ như đối với câu lệnh SELECT, UPDATE, DELETE cú pháp của JPA Query Language như sau:

```angular2html
SELECT ... FROM ...
[WHERE ...]
[GROUP BY ... [HAVING ...]]
[ORDER BY ...]

DELETE FROM ... [WHERE ...]

UPDATE ... SET ... [WHERE ...]
```

Điều này giúp chúng ta dễ dàng định nghĩa các câu query sử dụng JPA Query Language nhưng các bạn cũng nên 
nhớ một điều là: mặc dù chúng ta định nghĩa các câu query sử dụng các entity nhưng trong thực tế, 
lúc chạy, Hibernate hay bất kỳ thư viện nào implement JPA đều transform những câu query
đó sang những câu SQL dành cho database với tên cột, tên bảng của database đó.


Ta có 1 table như sau:
```angular2html
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
- Entity 

```angular2html 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Table
@Getter
@Setter
public class Student {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column
    private String name;
}
```
Sử dụng JPA Query Language để lấy thông tin từ bảng students ra thử xem sao nha các bạn.
Đầu tiên, chúng ta sẽ lấy đối tượng EntityManager trước:

```angular2html
EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexample");
EntityManager em = emf.createEntityManager();
```

Tiếp theo chúng ta sẽ sử dụng đối tượng EntityManager để lấy đối tượng Query dựa vào
thông tin của entity Student như sau:

```angular2html
Query query = em.createQuery("SELECT c FROM Student c");
```

Trong dòng code trên, chúng ta đã truyền cho phương thức createQuery() một câu JPA Query Language. 
Câu lệnh giống câu SQL chỉ có cái khác là lúc này nó sử dụng tên của entity của table students 
chứ không phải là tên của table students.

Ở đây chúng ta đã đặt cho entity Student một alias c giống như trong câu SQL. 
Alias này các bạn có thể sử dụng giống như một biến trong Java, nghĩa là nếu muốn lấy thông tin các 
thuộc tính của entity Student, các bạn có thể sử dụng “c.<tên_của_thuộc_tính>”. 
Ví dụ như nếu muốn lấy thông tin column name trong table students thì có thể viết lại câu
JPA Query Language như sau:

```angular2html
SELECT c.name FROM Student c
```

Bây giờ, sau khi đã có đối tượng Query rồi, các bạn có thể sử dụng nó để lấy kết quả mà các bạn muốn như sau:
```angular2html
List<Student> resultList = query.getResultList();
```

Đối tượng Query này có rất nhiều method, tuỳ theo câu JPA Query Language của bạn là gì thì các bạn
hãy sử dụng method của nó cho phù hợp. Ở đây, câu JPA Query Language là lấy tất cả các record 
trong bảng students, do đó mình sử dụng method getResultList().

Sau khi lấy xong, thì các bạn có thể in ra kết quả với đoạn code sau:

```angular2html
resultList.stream().forEach(c -> System.out.println(c.getName()));
```

Example:

```angular2html
import jakarta.persistence.*;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexample");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT c FROM Student c");
		List<Student> resultList = query.getResultList();

		resultList.stream().forEach(c -> System.out.println(c.getName()));
	}
}
```

## Spring Data JPA

Spring Data JPA, một phần của họ Spring Data lớn hơn, giúp dễ dàng triển khai các kho lưu trữ dựa 
trên JPA. Mô-đun này đề cập đến việc hỗ trợ nâng cao cho các lớp truy cập dữ liệu dựa trên JPA. 
Nó giúp việc xây dựng các ứng dụng hỗ trợ Spring sử dụng công nghệ truy cập dữ liệu trở nên dễ dàng hơn.

Việc triển khai lớp truy cập dữ liệu của một ứng dụng đã khá phức tạp trong một thời gian dài.
Phải viết quá nhiều mã soạn sẵn để thực hiện các truy vấn đơn giản cũng như thực hiện phân trang và
kiểm tra. Spring Data JPA nhằm mục đích cải thiện đáng kể việc triển khai các lớp truy cập dữ liệu 
bằng cách giảm nỗ lực đến mức thực sự cần thiết. Với tư cách là nhà phát triển, 
bạn viết các repository interfaces của mình, bao gồm các phương thức tìm kiếm tùy chỉnh và 
Spring sẽ tự động cung cấp việc triển khai.

Ngoài Spring Data JPA hỗ trợ cho JPA giảm thiểu code để truy cập và thao tác với các hệ thống quản trị 
cơ cở dữ liệu, chúng ta còn có Spring Data JDBC (cũng giống như Spring Data JPA), 
Spring Data LDAP (hỗ trợ Spring LDAP), Spring Data MongoDB (hỗ trợ cho MongoDB),… 
Các bạn có thể tìm thấy đầy đủ các module của Spring Data tại đây (https://spring.io/projects/spring-data/).

Để đạt được mục đích giảm thiểu code, Spring Data định nghĩa một interface chính tên là Repository 
nằm trong module Spring Data Common, module này sẽ được sử dụng cho tất cả các module còn lại trong Spring Data project. 
Nội dung của interface này đơn giản như sau:

```angular2html
import org.springframework.stereotype.Indexed;

@Indexed
public interface Repository<T, ID> {

}
```
Interface này sử dụng 2 generic type:

- T là domain class mà repository sẽ quản lý
- ID là kiểu dữ liệu của ID của domain class mà repository quản lý.

Vì interface này đơn giản như vậy nên chắc chắn nó phải có những interface khác extend nó để thể hiện
Spring Data project.

Ở đây, chúng ta sẽ có nhiều interface khác extend từ interface repository tuỳ thuộc vào module mà 
chúng ta sử dụng nhưng do chúng ta đang thảo luận về Spring Data JPA nên mình sẽ chỉ liệt kê ở đây
một interface duy nhất extend interface Repository mà Spring Data JPA đang sử dụng. 
Đó chính là interface CrudRepository.

Interface CrudRepository với ý nghĩa create, read, update, delete cho phép chúng ta thực hiện các thao tác
cơ bản đến với các hệ thống data, hệ thống data ở đây các bạn phải hiểu theo nghĩa rộng nghĩa là nó 
không chỉ là database.

Để hỗ trợ việc phân trang và sắp xếp cho Spring Data JPA, chúng ta dùng đến interface khác là 
PagingAndSortingRepository.

Tất cả các interface vừa kể trên đều nằm trong module Spring Data Common.

Trong Spring Data JPA, ở đây, chúng ta chỉ có duy nhất một interface là JpaRepository kế thừa 
interface PagingAndSortingRepository. Với việc extend từ interface PagingAndSortingRepository, 
các bạn cũng có thể hình dung là Spring Data JPA có thể giúp chúng ta giảm thiểu code cho các 
thao tác nào liên quan đến database

Có thể kể ra đây là: create, read, update, delete, paging và sort.

Ví dụ với interface JpaRepository:

Tạo mới một interface tên là HelloRepository extend từ Jpa Repository với nội dung như sau:
```angular2html
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface HelloRepository extends JpaRepository<Student, Long> {
    public Student findByName(String name);
}
```

Domain class ở đây chính là tên entity Student còn Long chính là kiểu dữ liệu của properties trong
entity Student mapping với column primary key trong table student.

Mặc dù không có bất kỳ class nào implement interface HelloRepository nhưng chúng ta vẫn truy vấn table
student theo column name được.

(https://github.com/spring-projects/spring-data-examples/tree/main/jpa/example/src/main/java/example/springdata/)

Bây giờ, chúng ta sẽ liệt kê tất cả các quy tắc để đặt tên method trong Spring Data JPA 
giúp chúng ta không cần phải implement interface mà vẫn lấy được dữ liệu chúng ta cần.

Đầu tiên thì tên của method, các bạn phải viết nó bắt đầu với một trong các tên như sau:
find…By, read…By, query…By, count…By, và get…By. 
Và theo sau đó là tên thuộc tính của entity mà các bạn muốn tìm.

Giả sử bây giờ mình tìm thông tin sinh viên theo lớp như sau:
```angular2html
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface StudentRepository extends JpaRepository<Student, Long> {
 
public List<Student> readByClasses(String classes);
}
```

Hoặc tìm số sinh viên tên như sau:
```angular2html
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface StudentRepository extends JpaRepository<Student, Long> {
    public Integer countByName(String name);
}
```

Nếumuốn giới hạn số record trả về thì các bạn có thể thêm từ First hoặc Top kèm với số record mà 
các bạn muốn trả về vào trước từ By trong tên của phương thức theo cú pháp trên.

Nếu không có số record thì mặc định chỉ có 1 record được trả về.

Ví dụ như:

```angular2html
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
	public Student findFirstByName(String name);
}
```

Nếu muốn tìm theo nhiều điều kiện khác nhau thì tên của phương thức phải kèm theo nhiều tên thuộc tính
của entity và ứng với đó các bạn phải có nhiều parameter cho phương thức.

Các parameter này phải theo thứ tự như thứ tự của tên thuộc tính trong tên phương thức.

Ví dụ giả sử các bạn tìm theo tên và lớp của sinh viên thì tên phương thức của các bạn cần phải là findByNameAndClasses(String name, String classes), 2 parameter name và classes phải cùng thứ tự như tên phương thức.
```angular2html
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByNameAndClazz(String name, String clazz);
}
```

Chúng ta còn có thể order kết quả trả về bằng cách thêm từ OrderBy…Asc (tăng dần)
hoặc OrderBy…Desc (giảm dần) vào cuối tên phương thức.

Trong dấu 3 chấm sẽ là tên thuộc tính của entity mà chúng ta sử dụng để order.
```angular2html
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public List<Student> findByClazzOrderByNameDesc(String clazz);
}
```

Chúng ta có thể custom câu lệnh SQL trong hàm findBy... ví dụ:

```angular2html
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
	 * Returns all users with the given firstname. This method will be translated into a query using the one declared in
	 * the {@link Query} annotation declared one.
	 *
	 * @param firstname
	 * @return
	 */
	@Query("select u from User u where u.firstname = :firstname")
	List<User> findByFirstname(String firstname);

    /**
    * Returns all users with the given name as first- or lastname. This makes the query to method relation much more
    * refactoring-safe as the order of the method parameters is completely irrelevant.
    *
    * @param name
    * @return
    */
    @Query("select u from User u where u.firstname = :name or u.lastname = :name")
    List<User> findByFirstnameOrLastname(String name);

}

```



## Custom Repository

Ngoài ra với các câu lệnh phức tạp hơn đòi hỏi các câu lệnh sql phức tạp hơn thì chúng ta có thể tự 
custom repository riêng mà ko cần phải implement từ JpaRepository

Chúng ta tạo ra 1 UserRepositoryCustom như sau:

```angular2html

import java.util.List;

/**
 * Interface for repository functionality that ought to be implemented manually.
 */
interface UserRepositoryCustom {

	/**
	 * Custom repository operation.
	 *
	 * @return
	 */
	List<User> myCustomBatchOperation();
}
```

Sau đó chúng ta implement lại interface này bằng class UserRepositoryImpl

```angular2html

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

class UserRepositoryImpl implements UserRepositoryCustom {
	public List<User> myCustomBatchOperation() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexample");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT c FROM Student c");

        return query.getResultList();
	}
}
```






