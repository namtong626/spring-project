package springbootproject.springboot.seeds;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import springbootproject.springboot.contracts.repositories.RoleRepositoryInterface;
import springbootproject.springboot.contracts.repositories.UserRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.*;
import springbootproject.springboot.enums.Language;
import springbootproject.springboot.models.Role;
import springbootproject.springboot.models.User;

import java.util.Date;
import java.util.List;

@Component
public class UserProfileSeeder {
    private static final Logger LOGGER = LogManager.getLogger(UserProfileSeeder.class);
    private final UserRepositoryInterface userRepo;
    private final JdbcTemplate jdbcTemplate;
    private final RoleRepositoryInterface roleRepo;
    private final JobCategoryRepositoryInterface jobCategoryRepo;
    private final ProfileRepositoryInterface profileRepo;
    private final UserCertificateRepositoryInterface userCerRepo;
    private final UserEducationRepositoryInterface userEduRepo;
    private final UserExperienceRepositoryInterface userExRepo;
    private final UserLanguageRepositoryInterface userLangRepo;
    private final WelfareRepositoryInterface welfareRepo;

    @Autowired
    public UserProfileSeeder(
            JdbcTemplate jdbcTemplate,
            UserRepositoryInterface userRepositoryInterface,
            RoleRepositoryInterface roleRepositoryInterface,
            JobCategoryRepositoryInterface jobCategoryRepositoryInterface,
            ProfileRepositoryInterface profileRepositoryInterface,
            UserCertificateRepositoryInterface userCertificateRepositoryInterface,
            UserEducationRepositoryInterface userEducationRepositoryInterface,
            UserExperienceRepositoryInterface userExperienceRepositoryInterface,
            UserHighlightSkillRepositoryInterface userHighlightSkillRepositoryInterface,
            UserLanguageRepositoryInterface userLanguageRepositoryInterface,
            UserOutstandingAchievementRepositoryInterface userOutstandingAchievementRepositoryInterface,
            WelfareRepositoryInterface welfareRepositoryInterface
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepo = userRepositoryInterface;
        this.roleRepo = roleRepositoryInterface;
        this.jobCategoryRepo = jobCategoryRepositoryInterface;
        this.profileRepo = profileRepositoryInterface;
        this.userCerRepo = userCertificateRepositoryInterface;
        this.userEduRepo = userEducationRepositoryInterface;
        this.userExRepo = userExperienceRepositoryInterface;
        this.userLangRepo = userLanguageRepositoryInterface;
        this.welfareRepo = welfareRepositoryInterface;
    }

    @EventListener
    public void seeds(ContextRefreshedEvent event) {
        seedUsersTable();

    }

    private void seedUsersTable() {
        String sql = "SELECT email FROM users U WHERE U.email = \"admin@mail.com\" LIMIT 1";
        List<Object> u = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if(u.isEmpty()) {
            User user = new User();
            user.setFirstname("Nam");
            user.setLastname("Tong");
            user.setEmail("admin@mail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user.setAddress("Quan BT, Tp HCM");
            user.setAvatar("/images/avatar.png");
            user.setPhone("0945151206");
            user.setDob(new Date());
            user.setEmail_verified_at(new Date());
            user.setLastname(Language.VI.toString());

            Role role = new springbootproject.springboot.models.Role();
            role.setName(springbootproject.springboot.enums.Role.ROLE_ADMIN.toString());

            this.roleRepo.save(role);

            user.setRoles(List.of(role));

            this.userRepo.save(user);

            LOGGER.info("Users Seeded");
        } else {
            LOGGER.trace("Users Seeding Not Required");
        }
    }
}
