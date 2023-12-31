package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springbootproject.springboot.models.User;

import java.util.List;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
        User findByEmail(String email);

        // @Query(value = "\n" +
        // "SELECT users.*, roles.name AS role_name\n" +
        // "FROM users\n" +
        // "INNER JOIN users_roles ON users_roles.user_id = users.id\n" +
        // "INNER JOIN roles ON users_roles.role_id = roles.id\n" +
        // "WHERE roles.name = :roleName " +
        // "AND (:keyword IS NULL OR CONCAT(firstname, ' ', lastname) LIKE CONCAT('%',
        // :keyword, '%')) \n" +
        // "LIMIT :limit", nativeQuery = true)
        // List<User> findByConditions(String roleName, String keyword, int limit);

        @Query(value = "\n" + "Select u.*" +
                        "from users as u\n" +
                        "Limit :pageNumber,:pageSize", nativeQuery = true)
        List<User> findUsers(int pageNumber, Integer pageSize);

        @Query(value = "\n" + "Select u.*" +
                        "from users as u\n" +
                        "where CONCAT(firstname, ' ', lastname) LIKE %:keyWord% \n" +
                        "Limit :pageNumber,:pageSize", nativeQuery = true)
        List<User> findUsersByKeyWord(int pageNumber, Integer pageSize, String keyWord);

        @Query(value = "\n" +
                        "SELECT users.*, roles.name AS role_name\n" +
                        "FROM users\n" +
                        "INNER JOIN users_roles ON users_roles.user_id = users.id\n" +
                        "INNER JOIN roles ON users_roles.role_id = roles.id\n" +
                        "WHERE roles.name = :roleName " +
                        "AND (:keyword IS NULL OR CONCAT(firstname, ' ', lastname) LIKE CONCAT('%', :keyword, '%') OR email LIKE :keyword) \n"
                        +
                        "LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<User> findByConditions(String roleName, String keyword, int offset, int limit);

        @Query(value = "\n" +
                        "SELECT COUNT(*)\n" +
                        "FROM users\n" +
                        "INNER JOIN users_roles ON users_roles.user_id = users.id\n" +
                        "INNER JOIN roles ON users_roles.role_id = roles.id\n" +
                        "WHERE roles.name = :roleName " +
                        "AND (:keyword IS NULL OR CONCAT(firstname, ' ', lastname) LIKE CONCAT('%', :keyword, '%') OR email LIKE :keyword) \n", nativeQuery = true)
        int countByConditions(String roleName, String keyword);
}
