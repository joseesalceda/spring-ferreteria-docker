package com.jose.spring.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jose.spring.hibernate.Entities.Usuarios;
import com.jose.spring.hibernate.Repositorios.IUsuarios;

@Component
@Profile("dev")
public class DevUserInitializer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DevUserInitializer.class);

    private final IUsuarios repoUsuarios;
    private final PasswordEncoder passwordEncoder;

    public DevUserInitializer(IUsuarios repoUsuarios, PasswordEncoder passwordEncoder) {
        this.repoUsuarios = repoUsuarios;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (repoUsuarios.findByUser("jose").isPresent()) {
            logger.info("El usuario de desarrollo 'jose' ya existe.");
            return;
        }

        Usuarios usuario = new Usuarios();
        usuario.setUser("jose");
        usuario.setPassword(passwordEncoder.encode("jose"));
        usuario.setRol("USER");
        repoUsuarios.save(usuario);
        logger.info("Creado el usuario de desarrollo 'jose' con rol USER.");
    }
}
