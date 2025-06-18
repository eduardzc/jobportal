package com.eduardz.jobportal.config;

import com.eduardz.jobportal.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthenticationSucessHandler customAuthenticationSucessHandler;

    private final String[] publicUrls = {
            "/",
            "global-search/**",
            "/register",
            "/register/**",
            "webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**", "/favicon.ico", "/resources/**", "/error"
    };

    public WebSecurityConfig(CustomUserDetailsService userDetailsService,
                             CustomAuthenticationSucessHandler customAuthenticationSucessHandler) {
        this.userDetailsService = userDetailsService;
        this.customAuthenticationSucessHandler = customAuthenticationSucessHandler;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth ->{
            auth.requestMatchers(publicUrls).permitAll(); // Acceso libre a URLs públicas
            auth.anyRequest().authenticated(); // Cualquier otra ruta requiere autenticación
        });

        http.formLogin(form -> form.loginPage("/login").permitAll()
                .successHandler(customAuthenticationSucessHandler))
                .logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/");
                }).cors(Customizer.withDefaults())
                .csrf(csrf->csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
