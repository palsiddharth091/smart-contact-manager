package com.scm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm20.service.implementation.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService customUserDetailService;

    // private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    // User Create and Login Using Java Code with in memory service
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(); // Just for Testing Pupose
    //     return inMemoryUserDetailsManager;
    // }

    @Bean
    public AuthenticationProvider authenticationProivder(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailService);
        daoAuthenticationProvider.setUserDetailsPasswordService(null);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorize ->{
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login");  // This is to tell spring security to use the customised page for login.
            formLogin.loginProcessingUrl("/authenticate"); // The URL in which we will send our data once we hit the login button to let us log in. 
            formLogin.defaultSuccessUrl("/user/dashboard"); // Redirection to the URL post successful login
            formLogin.failureUrl("/login?error=true");
            formLogin.usernameParameter("username");
            formLogin.passwordParameter("password"); 
            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // The method that gets executed when there is a success during login
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });

            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // The method that gets executed when there is a failure during login
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable); // Disabling this because while loggin out we need to hit POST URL and not GET while logging out
        httpSecurity.logout(formLogout->{
            formLogout.logoutUrl("/logout");
            formLogout.logoutSuccessUrl("/login?logout=true");
        });
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
