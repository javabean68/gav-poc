package ch.egroup.interview.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity  // Redundant in Spring Boot app
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("spring")
                .password(encoder.encode("secret"))
                .roles("USER");
    }

    @Override
    /**
     * you have to disable csrf Protection because it is enabled by default in spring security:
     * here you can see code that allow cors origin
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/people")
                .authenticated()
                //.antMatchers("/**")
                //.permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}