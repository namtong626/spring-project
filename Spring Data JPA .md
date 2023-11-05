# Spring Data JPA
(https://spring.io/projects/spring-data-jpa/)

Spring Data JPA giúp chúng ta đơn giản hoá quá trình viết câu lệnh SQL để thao tác với database thông qua cơ chế tự động tạo câu lệnh SQL từ code Java.

## JPA Query Language
Vì mình sẽ sử dụng JPA với implementation của Hibernate, do đó, mình sẽ thêm Hibernate dependencies như sau:

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


## EntityManager 
EntityManager là một interface  được cung cấp bởiJava Persistence API (JPA). Chúng ta sử dụng EntityManager có mục đích chung để quản lý vòng đời của các phiên bản entity, chẳng hạn như:
Tạo và xóa các phiên bản entity
Tìm các entity theo khóa chính của chúng
Truy vấn các entity
Trong ứng dụng Spring Boot sử dụng Spring Data JPA, bạn có thể đưa một phiên bản EntityManager vào lớp kho lưu trữ/dịch vụ/bộ điều khiển của mình. Bộ chứa IoC của Spring quản lý một Bean EntityManager và việc triển khai cụ thể được cung cấp bởi khung Hibernate.
Một đối tượng EntityManager quản lý một tập hợp các thực thể được xác định bởi đơn vị kiên trì. Và người quản lý thực thể chịu trách nhiệm theo dõi tất cả các đối tượng thực thể để biết các thay đổi và đồng bộ hóa các thay đổi với cơ sở dữ liệu.
 ### Inject an EntityManager object

```
import javax.persistence.EntityManager;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class ContactRepository {
    @Autowired private EntityManager entityManager;
 
}
```

- EntityManager Create Entity Example

```
  import javax.persistence.EntityManager;
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class ContactRepository {
 
    @Autowired private EntityManager entityManager;
 
    @Transactional
    public void save(Contact contact) {
        entityManager.persist(contact);
    }
}
```


```
@SpringBootApplication
public class Application implements CommandLineRunner {
     
    @Autowired private ContactRepository repo;
     
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
 
        createContact();
    }
 
    private void createContact() {
        Contact newContact = new Contact();
 
        newContact.setName("Peter Smith");
        newContact.setEmail("peter.smith@gmail.com");
        newContact.setAddress("New York, USA");
        newContact.setPhone("123456-2111");
         
        repo.save(newContact);     
    }
     
}
```

- EntityManager Update Entity Example

```
@Transactional
public Contact update(Contact contact) {
    return entityManager.merge(contact);
}
```
```
@Override
public void run(String... args) throws Exception {
 
    updateContact();
 
}
     
private void updateContact() {
    Contact existContact = new Contact();
     
    existContact.setId(1);
    existContact.setName("Peter Smith");
    existContact.setEmail("peter.smith@gmail.com");
    existContact.setAddress("New York, USA");
    existContact.setPhone("123456-2111");
     
    Contact updatedContact = repo.update(existContact);
     
}
```
- EntityManager Query Entities Example
  ```
  import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class ContactRepository {
 
    @PersistenceContext private EntityManager entityManager;
     
    public List<Contact> findAll() {
        String jpql = "SELECT c FROM Contact c";
        TypedQuery<Contact> query = entityManager.createQuery(jpql, Contact.class);
         
        return query.getResultList();
    }
 
}
```
```
@Override
public void run(String... args) throws Exception {
 
    listContacts();
}
 
private void listContacts() {
    List<Contact> listContacts = repo.findAll();
    listContacts.forEach(System.out::println);     
}
```
- EntityManager Find Entity by ID Example
```
public Contact findById(Integer id) {
    return entityManager.find(Contact.class, id);
}
```

```
@Override
public void run(String... args) throws Exception {
    getContact();
}
 
private void getContact() {
    Integer contactId = 1;
    Contact contact = repo.findById(contactId);
     
    System.out.println(contact);
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






