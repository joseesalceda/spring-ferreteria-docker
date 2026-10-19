package com.jose.spring.hibernate.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jose.spring.hibernate.Services.ServicioUsuario;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig {

    final private ServicioUsuario servicioUsuario;

    public SecurityConfig(ServicioUsuario servicioUsuario){

        this.servicioUsuario = servicioUsuario;

    }

    @Bean
    public PasswordEncoder codificaPass(){

       return new BCryptPasswordEncoder(); 

    }
    
    @Bean
    public AuthenticationManager autenticacion(AuthenticationConfiguration authConfig) throws Exception{

        return authConfig.getAuthenticationManager();
    }

    @Bean 
    public SecurityFilterChain securityChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{

        http.csrf(csfr -> csfr.disable())
            .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/articulos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/articulo/*").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                        .authenticationManager(authManager)
                        .userDetailsService(servicioUsuario)
                        .formLogin(form -> form.permitAll())
                        .httpBasic(basic -> {});
               

                return http.build();
    }

}
