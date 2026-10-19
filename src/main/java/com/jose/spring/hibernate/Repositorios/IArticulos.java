package com.jose.spring.hibernate.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.spring.hibernate.Entities.Articulos;

public interface IArticulos extends JpaRepository<Articulos, Long> {

    public Optional<Articulos> findByNombre(String nombre);

}
