package com.jose.spring.hibernate.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jose.spring.hibernate.DTOs.ArticulosDTO;
import com.jose.spring.hibernate.Entities.Articulos;
import com.jose.spring.hibernate.Exceptions.NotFoundArticleException;
import com.jose.spring.hibernate.Mappers.MapperDTO;
import com.jose.spring.hibernate.Repositorios.IArticulos;

@Service 
public class ServicioArticulo {

    final private IArticulos iArticulos;

    public ServicioArticulo(IArticulos iArticulos){

        this.iArticulos = iArticulos;

    }


    public List<ArticulosDTO> allArticles(){

        return iArticulos.findAll().stream()
                            .map(articulo -> MapperDTO.toDTO(articulo))
                            .toList();
        
    }

    public Optional<ArticulosDTO> articuloID(Long id){

        return iArticulos.findById(id).map(MapperDTO::toDTO);

    }

    public Optional<ArticulosDTO> articuloNombre(String nombre){
        
        return iArticulos.findByNombre(nombre).map(articulo -> MapperDTO.toDTO(articulo));

    }

    public ArticulosDTO nuevoArticulo(ArticulosDTO articuloDTO){

        Articulos newArticulo = MapperDTO.toArtic(articuloDTO);
        Articulos saverdArt = iArticulos.save(newArticulo);
        return MapperDTO.toDTO(saverdArt);

    }

    public ArticulosDTO actualizaArticulo(Long id, ArticulosDTO dto){

        Articulos articulo = iArticulos.findById(id)
                            .orElseThrow(()-> new NotFoundArticleException(id));
        
        articulo.setNombre(dto.getNombre());
        articulo.setPrecio(dto.getPrecio());
        articulo.setExistencia(dto.getExistencia());

        Articulos actualizado = iArticulos.save(articulo);
        return MapperDTO.toDTO(actualizado);
       

    }

    //Borro mediante el propio Articulo. De esta manera, obtengo el objeto Articulo.
    public void borraArticulo(Long id){

        Articulos articulo = iArticulos.findById(id)
                            .orElseThrow(()-> new NotFoundArticleException(id));

        iArticulos.delete(articulo);

    }

    //Borro por Id del articulo. De esta forma no necesito rescatar el Articulo.
    public void borraArticuloV2(Long id){

        if (iArticulos.existsById(id)) {

            iArticulos.deleteById(id);
            
        } else {

            throw new NotFoundArticleException(id);

        }

    }

}
