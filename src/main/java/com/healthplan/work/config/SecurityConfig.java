package com.healthplan.work.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig implements WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // /admin/** 접근은 ADMIN 권한을 가진 사용자만 가능
                .antMatchers("/user/**").authenticated() // /user/** 접근은 인증된 사용자만 가능
                .anyRequest().permitAll()
                .and()
                .formLogin() // 로그인 설정
                .loginPage("/login")
                .loginProcessingUrl("/loginProc") // 실제 로그인 처리 엔드 포인트
                .usernameParameter("email") // form에서 email 사용
                .defaultSuccessUrl("/loginOk")
                .and()
                .logout() // 로그아웃 설정
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutOk")
                .deleteCookies("JSESSIONID");

        http.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");// 리액트 서버
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

}