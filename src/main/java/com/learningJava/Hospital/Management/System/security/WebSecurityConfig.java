package com.learningJava.Hospital.Management.System.security;


import com.learningJava.Hospital.Management.System.entity.type.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static com.learningJava.Hospital.Management.System.entity.type.RoleType.*;
import static com.learningJava.Hospital.Management.System.entity.type.PermissionType.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableMethodSecurity
public class WebSecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;
        private final OAuth2SuccessHandler oAuth2SuccessHandler;
        private final HandlerExceptionResolver handlerExceptionResolver;
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(csrfConfig -> csrfConfig.disable())
                                .sessionManagement(sessionConfig -> sessionConfig
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/public/**", "/auth/**").permitAll()
                                                // .requestMatchers("/admin/**").authenticated()
                                        .requestMatchers(HttpMethod.DELETE, "/admin/**")
                                        .hasAnyAuthority(APPOINTMENT_DELETE.name(),
                                                USER_MANAGE.name())
                                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                                        .requestMatchers("/doctors/**").hasAnyRole(DOCTOR.name(), ADMIN.name())
                                                .anyRequest().authenticated())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        .oauth2Login(oAuth2 -> oAuth2
                                .failureHandler((request, response, exception) -> {
                                        log.error("OAuth2 error: {}", exception.getMessage());
                                        handlerExceptionResolver.resolveException(request, response, null, exception);
                                })
                                .successHandler(oAuth2SuccessHandler)
                        )
                        .exceptionHandling(exceptionHandlingConfigurer ->
                                exceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                                    handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
                                }));



            return http.build();
        }

        // @Bean
        // public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        //
        // UserDetails admin = User
        // .withUsername("admin")
        // .password(encoder.encode("123"))
        // .roles("ADMIN")
        // .build();
        //
        // return new InMemoryUserDetailsManager(admin);
}
