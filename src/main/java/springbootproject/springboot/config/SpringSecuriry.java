package springbootproject.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecuriry {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/build/**").permitAll()
                                .requestMatchers("/dist/**").permitAll()
                                .requestMatchers("/plugins/**").permitAll()
                                .requestMatchers("/dashboard").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/customers/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/users/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/users1/**").hasAuthority("ROLE_ADMIN")

                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard")
                        .permitAll()
                ).logout(
                        logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                )
                .build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}

