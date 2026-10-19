package com.jose.spring.hibernate.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.spring.hibernate.Entities.Usuarios;

public interface IUsuarios extends JpaRepository<Usuarios, Long>{

    public Optional<Usuarios> findByUser(String user);

}
