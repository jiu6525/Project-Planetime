package search.planetime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import search.planetime.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.
                csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/home","/member").permitAll();
        http
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("memberId")
                .passwordParameter("memberPwd")
                .successHandler(new MyLoginSuccessHandler())
            .and()
                .logout()
                .logoutUrl("/doLogout")
                .invalidateHttpSession(true)
                .permitAll()
                .logoutSuccessUrl("/main")
            .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .successHandler(new MyLoginSuccessHandler())
                // 구글 로그인이 완료된 뒤의 후처리가 필요. Tip. 코드 X, (엑세스 토큰 + 사용자 프로필 정보 O)
                // 1.코드받기(인증), 2.엑세스토큰(권한)
                // 3.사용자 프로필 정보 가져옴
                // 4-1. 그 정보로 회원가입 자동 진행
                // 4-2. (이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> (집주소),백화점몰 -> (vip 등급, 일반등급)
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

        return http.build();
    }
    //passwordEncoder
//    @Bean
//    public BCryptPasswordEncoder encodePassword() {
//        return new BCryptPasswordEncoder();
//    }

}