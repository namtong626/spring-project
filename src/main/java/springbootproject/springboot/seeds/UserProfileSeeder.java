package springbootproject.springboot.seeds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springbootproject.springboot.contracts.repositories.CountryRepositoryInterface;
import springbootproject.springboot.contracts.repositories.RoleRepositoryInterface;
import springbootproject.springboot.contracts.repositories.UserRepositoryInterface;
import springbootproject.springboot.contracts.repositories.profiles.*;
import springbootproject.springboot.enums.*;
import springbootproject.springboot.helpers.Common;
import springbootproject.springboot.models.*;
import springbootproject.springboot.models.Role;

import java.util.*;

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
    private final CountryRepositoryInterface countryRepo;
    private final UserHighlightSkillRepositoryInterface userHighlightSkillRepo;
    private final UserOutstandingAchievementRepositoryInterface userOutstandingAchievementRepo;

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
            WelfareRepositoryInterface welfareRepositoryInterface,
            CountryRepositoryInterface countryRepositoryInterface) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepo = userRepositoryInterface;
        this.roleRepo = roleRepositoryInterface;
        this.jobCategoryRepo = jobCategoryRepositoryInterface;
        this.profileRepo = profileRepositoryInterface;
        this.userCerRepo = userCertificateRepositoryInterface;
        this.userEduRepo = userEducationRepositoryInterface;
        this.userExRepo = userExperienceRepositoryInterface;
        this.userHighlightSkillRepo = userHighlightSkillRepositoryInterface;
        this.userOutstandingAchievementRepo = userOutstandingAchievementRepositoryInterface;
        this.userLangRepo = userLanguageRepositoryInterface;
        this.welfareRepo = welfareRepositoryInterface;
        this.countryRepo = countryRepositoryInterface;
    }

    @EventListener
    @Transactional
    public void seeds(ContextRefreshedEvent event) {
        seedCountries();

        if (Common.isTableEmpty(userRepo)) {
            seedUsersTable();

            for (int i = 0; i <= 100; i++) {
                seedCustomersTable();
            }
        }

    }

    private void seedUsersTable() {
        String sql = "SELECT email FROM users U WHERE U.email = \"admin@mail.com\" LIMIT 1";
        List<Object> admin = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (admin.isEmpty()) {
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
            user.setLanguage(Language.valueOf(Language.VI.toString()));

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

    private void seedCustomersTable() {
        User user = new User();
        user.setFirstname(Common.generateRandomChars(10, null));
        user.setLastname(Common.generateRandomChars(10, null));
        user.setEmail(Common.generateRandomChars(10, null) + "@mail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setAddress("Quan BT, Tp HCM");
        user.setAvatar("/images/avatar.png");
        user.setPhone(Common.generateRandomChars(10, "0123456789"));
        user.setDob(new Date());
        user.setEmail_verified_at(new Date());
        user.setLanguage(Language.valueOf(Language.VI.toString()));

        Role role = setRoleUser();

        user.setRoles(List.of(role));

        this.userRepo.save(user);

        Profile profile = setProfileUser(user);
        setUserExperience(profile);
        setUserEducation(profile);
        setUserLanguage(profile);
        setUserHighlightSkill(profile);
        setUserOutstandingAchievement(profile);
        setCertificate(profile);

        LOGGER.info("Customers Seeded");
    }

    private Profile setProfileUser(User user) {
        Profile profile = new Profile();
        profile.setTitle("profile title  cua New Customer");
        profile.setCareer_goals("profile Career goals  cua New Customer");
        profile.setReceive_job_alert(0);
        profile.setShow_profile(0);
        profile.setPosition_expected(PositionExpected.valueOf(PositionExpected.MANAGER.toString()));
        profile.setSalary_from(1000000.0);
        profile.setSalary_to(5000000.0);
        profile.setWorking_form(1);

        List<JobCategory> jobCategory = setJobCategory();
        profile.setJobCategory(jobCategory);

        List<Welfare> welfareList = setWelfare();
        profile.setWelfare(welfareList);

        profile.setUser(user);

        this.profileRepo.save(profile);

        return profile;
    }

    private void setCertificate(Profile profile) {
        UserCertificate userCertificate = new UserCertificate();
        userCertificate.setName("Chung chi dinh");
        userCertificate.setIssued_by("Chung chi by ABC");
        userCertificate.setStart_date(new Date());
        userCertificate.setDue_date(new Date());
        userCertificate.setProfile(profile);

        this.userCerRepo.save(userCertificate);

    }

    private void setUserOutstandingAchievement(Profile profile) {
        UserOutstandingAchievement userOutstandingAchievement = new UserOutstandingAchievement();
        userOutstandingAchievement.setDescription("Giai nhat giai cham chi");
        userOutstandingAchievement.setProfile(profile);

        this.userOutstandingAchievementRepo.save(userOutstandingAchievement);

    }

    private void setUserHighlightSkill(Profile profile) {
        UserHighlightSkill userHighlightSkill = new UserHighlightSkill();
        userHighlightSkill.setSkills_expertise("Lam viec cham chi");
        userHighlightSkill.setDescription("Lam viec cham chi nhat ");
        userHighlightSkill.setLevel(1);
        userHighlightSkill.setProfile(profile);

        this.userHighlightSkillRepo.save(userHighlightSkill);

    }

    private void setUserLanguage(Profile profile) {
        UserLanguage userLanguage = new UserLanguage();
        userLanguage.setQualification(LanguageQualification.valueOf(LanguageQualification.ADVANCED.toString()));
        userLanguage.setDescription("Rat tot");

        Country country = this.countryRepo.findByName(CountryEnums.USA.name().toLowerCase());
        userLanguage.setCountry(country);
        userLanguage.setProfile(profile);

        this.userLangRepo.save(userLanguage);

    }

    private void setUserEducation(Profile profile) {
        UserEducation userEducation = new UserEducation();
        userEducation.setName("Trương dại học");
        userEducation.setDegree(Degree.valueOf(Degree.UNIVERSITY.toString()));
        userEducation.setDescription("Hoc gioi");
        userEducation.setProfile(profile);

        this.userEduRepo.save(userEducation);

    }

    private void setUserExperience(Profile profile) {
        UserExperience userExperience = new UserExperience();
        userExperience.setPosition("Nhan vien ban hang");
        userExperience.setCompany("Cong ty ABC");
        userExperience.setPosition_expected(PositionExpected.valueOf(PositionExpected.DIRECTOR.toString()));
        userExperience.setStart_date(new Date());
        userExperience.setEnd_date(new Date());
        userExperience.setDescription("Ban rat nhieu hang");
        userExperience.setProfile(profile);

        this.userExRepo.save(userExperience);

    }

    private List<Welfare> setWelfare() {
        List<Welfare> welfareList = new ArrayList<>();

        if (Common.isTableEmpty(welfareRepo)) {
            String[] welfares = { "luong thang 13", "bao hiem", "thuong doanh so", };

            for (int i = 0; i < welfares.length; i++) {
                Welfare welfare = new Welfare();
                welfare.setName(welfares[i]);
                welfareList.add(welfare);
            }

            this.welfareRepo.saveAll(welfareList);
        }

        return welfareList;
    }

    private Role setRoleUser() {
        Role role = this.roleRepo.findByName(springbootproject.springboot.enums.Role.ROLE_END_USER.toString());

        if (role == null) {
            Role newRole = new Role();
            newRole.setName(springbootproject.springboot.enums.Role.ROLE_END_USER.toString());
            this.roleRepo.save(newRole);

            return newRole;
        }

        return role;
    }

    private List<JobCategory> setJobCategory() {
        List<JobCategory> jobCategoryList = new ArrayList<>();

        if (Common.isTableEmpty(jobCategoryRepo)) {
            String[] jobCategories = Common.getEnumNames(JobCategoryEnum.class);

            for (int i = 0; i < jobCategories.length; i++) {
                JobCategory jobCategory = new JobCategory();
                jobCategory.setName(jobCategories[i].toLowerCase());
                jobCategoryList.add(jobCategory);
            }

            this.jobCategoryRepo.saveAll(jobCategoryList);
        }

        return jobCategoryList;
    }

    private void seedCountries() {
        if (!Common.isTableEmpty(countryRepo)) {
            return;
        }

        List<Country> countryList = new ArrayList<>();

        for (CountryEnums dir : CountryEnums.values()) {
            Country country = new Country();
            country.setName(String.valueOf(dir).toLowerCase());
            country.setLanguage(dir.getLanguage());
            countryList.add(country);
        }

        this.countryRepo.saveAll(countryList);
    }
}
