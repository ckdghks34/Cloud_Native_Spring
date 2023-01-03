package com.ecommerce.userservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * CSRF
         * 사이즈간 위조 요청으로, 즉 정상적인 사용자가 의도치 않은 위조요청을 보내는 것을 의미
         * CSRF protection은 default로 설정된다.
         * protection을 통해 Get을 제외한 POST, PUT, DELETE 요청으로 보호함.
         * enable할 경우 csrf토큰을 포함해야 요청을 받아드림.
         */
        http.csrf().disable();
        // /users 로 요청한 리소스의 접근을 인증절차없이 허용한다.
        http.authorizeHttpRequests().antMatchers("/users/**").permitAll();

        // 아래 코드를 작성하지 않으면 h2-console 접속시 연결거부함.
        http.headers().frameOptions().disable();
    }
}
