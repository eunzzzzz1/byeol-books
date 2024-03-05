package com.eunzzzzzi.byeolbooks.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/**").permitAll()
                .and()
                .csrf().ignoringRequestMatchers("/h2-console/**")
                .and()
                .headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .formLogin(formLogin -> formLogin
                        .loginPage("/user/signin") // 로그인 Form이 위치한 Url
                        .usernameParameter("user_id") // 아이디를 적는 input의 name과 일치하게
                        .passwordParameter("user_password") // 비밀번호를 적는 input의 name과 일치하게
                        .loginProcessingUrl("/user/signin") // Form의 Action 주소와 일치하게
                        .defaultSuccessUrl("/")); // 로그인 성공 시 이동하는 페이지
        return http.getOrBuild();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // 클래스가 아닌 메소드이지만, @Bean 어노테이션을 사용해주었기 때문에 객체처럼 사용할 수 있다.
    }

}
