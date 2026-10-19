package com.jose.spring.hibernate.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@JsonPropertyOrder({
    "id",
    "nombrearticulo",
    "precio",
    "existencia"
})
@Data  
@Entity
@Table(name="articulos")
public class Articulos {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombrearticulo")
    private String nombre;
    private Double precio;
    private Integer existencia;

}
