package com.example.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.annotation.Resource;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication() // creating user in memory
                .withUser("user")
                .password("123456").roles("p1")
//                .password(new BCryptPasswordEncoder().encode("password")).roles("USER")
                .and().withUser("admin")
                .password("admin").authorities("ROLE_ADMIN");*/
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().permitAll().defaultSuccessUrl("http://www.baidu.com").and()
//        http.formLogin().permitAll().defaultSuccessUrl("/oauth-service/login").and()
                .authorizeRequests()
                .antMatchers("/oauth-service/test/*").hasAuthority("p1")
                .antMatchers("/oauth-service/test2/*").permitAll()
//                .antMatchers("/oauth-service/test/user").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .formLogin()
//                .successForwardUrl("http://www.baidu.com");
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }

   /* @Bean
    public UserDetailsManager getUserDetails(){
        InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
        detailsManager.createUser(User.withUsername("user").password("123456").authorities("p1").build());
        return detailsManager;
    }*/


}
