package com.example.RedisBoard.config;

import com.example.RedisBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;
    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        // 유효성 검사
        http.authorizeRequests()
                .antMatchers("/board/addPage/**", "/board/post/updatePage/**", "/board/post/delete/**").hasRole("USER")
                // 그 이외 모둔 url 은 모든 접속자가 접근 가능
                .anyRequest().permitAll();

        // 권한이 없는 유저가 들어오면 이동할 경로
        http.exceptionHandling()
                .accessDeniedPage("/");

        // 'USER' 권한을 가진 유저가 로그인을 할 때 사용할 경로, 실패시 이동할 경로, 성공시 이동할 경로 작성
        http.formLogin()
                .loginPage("/loginPage/")
                .usernameParameter("id")
                .passwordParameter("password")
                .loginProcessingUrl("/loginPage/login/")
                .defaultSuccessUrl("/loginPage/login/loginSuccess/")
                .failureHandler(new AuthenticationFailureHandler() {
                    // 로그인 실패시 작동할 핸들러 생성
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        // 오류 확인하기
                        System.out.println("exception : " + exception.getMessage());
                        // 에러 메세지 변수 생성
                        String errorMessage = null;
                        // 에러 결과에 따라 에러 메세지 지정
                        if(exception instanceof BadCredentialsException){
                            errorMessage = "아이디 또는 비밀번호가 잘못 입력 되었습니다.";
                        }else{
                            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다.";
                        }
                        // 에러 메세지를 포함해서 다시 loginPage 로 이동
                        response.sendRedirect("/loginPage/?error=true&errorMessage=" + URLEncoder.encode(errorMessage, "UTF-8"));
                    }
                })
                .permitAll();

        // 로그아웃
        http.logout()
                .logoutUrl("/logout/")
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/resources/**", "/error", "/css/**", "/js/**");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
