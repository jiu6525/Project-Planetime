package search.planetime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .logoutSuccessUrl("/home1");

    }
    //passwordEncoder
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}