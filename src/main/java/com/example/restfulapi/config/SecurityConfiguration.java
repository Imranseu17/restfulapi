package com.example.restfulapi.config;


import com.example.restfulapi.repository.UsersRepository;
import com.example.restfulapi.service.CustomUsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUsersDetailsService customUsersDetailsService;




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUsersDetailsService).
                passwordEncoder(getPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("**/secured/**")
                .authenticated()
                .and().httpBasic().and()
                .formLogin()
                .permitAll();


    }



    private PasswordEncoder getPasswordEncoder() {

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {



               return new BCryptPasswordEncoder().encode(rawPassword.toString());
            }



            @Override
            public boolean matches(CharSequence rawPassword,
                                   String encodedPassword) {
                return true;
            }
        };
    }









}
