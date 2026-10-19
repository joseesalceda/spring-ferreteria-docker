package com.jose.spring.hibernate.Mappers;

import com.jose.spring.hibernate.DTOs.ArticulosDTO;
import com.jose.spring.hibernate.Entities.Articulos;

public class MapperDTO {

    public static ArticulosDTO toDTO(Articulos articulo){

        return new ArticulosDTO(articulo.getId(), articulo.getNombre(),
                                articulo.getPrecio(), articulo.getExistencia());

    }

    public static Articulos toArtic(ArticulosDTO articuloDTO){

        Articulos articulo = new Articulos();
        articulo.setPrecio(articuloDTO.getPrecio());
        articulo.setNombre(articuloDTO.getNombre());
        articulo.setExistencia(articuloDTO.getExistencia());

        return articulo;

    }

}
