package com.work.bookstoreapi;

import com.work.bookstoreapi.jwt.JwtTokenFilter;
import com.work.bookstoreapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

//@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;
    @Autowired private JwtTokenFilter jwtTokenFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User "+ username+ " not found "))
        );
    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(
                ((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, authException.getMessage());
                }));
        http.authorizeRequests()
                .antMatchers("/bookstore-api/user/auth/login",
                             "/bookstore-api/user/add-new",
                             "/bookstore-api/user/initiate-default",
                             "/bookstore-api/user/reset-password",
                             "/bookstore-api/user/change-password",
                             "/bookstore-api/user/forgot-password")
                .permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
