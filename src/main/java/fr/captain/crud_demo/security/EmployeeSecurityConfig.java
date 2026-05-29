package fr.captain.crud_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EmployeeSecurityConfig {

    // Creating 3 users
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails captain = User.builder()
                .username("captain")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails zelda = User.builder()
                .username("zelda")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails ganon = User.builder()
                .username("ganon")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(captain, zelda, ganon);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.authorizeHttpRequests(configurer -> {
                    configurer
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN");
                }
        );

        // HTTP basic authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable (Cross Site Request Forgery) CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
