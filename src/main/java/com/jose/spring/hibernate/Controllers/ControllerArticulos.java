package com.jose.spring.hibernate.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.spring.hibernate.DTOs.ArticulosDTO;
import com.jose.spring.hibernate.Services.ServicioArticulo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
;


@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api")
public class ControllerArticulos {

    final private ServicioArticulo servicioArticulo;

    public ControllerArticulos(ServicioArticulo servicioArticulo){

        this.servicioArticulo = servicioArticulo;

    }

    @GetMapping("/articulos")
    public List<ArticulosDTO> obtenerTodos(){

        return servicioArticulo.allArticles();

    }

    @GetMapping("/articulo/{id}")
    public ResponseEntity<ArticulosDTO> articuloId(@PathVariable Long id){

        return servicioArticulo.articuloID(id)
                        .map(articulo -> ResponseEntity.ok(articulo))
                        .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/articulos/nombre/{name}")
    public ResponseEntity<ArticulosDTO> articuloName(@PathVariable String name){

        return servicioArticulo.articuloNombre(name)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/articulos/articuloNew")
    public ResponseEntity<ArticulosDTO> creaArticulo(@RequestBody ArticulosDTO articuloDTO) {
        
        ArticulosDTO newArticulo = servicioArticulo.nuevoArticulo(articuloDTO);
        return ResponseEntity.status(201).body(newArticulo);

    }

    @PutMapping("/articulos/edit/{id}")
    public ResponseEntity<ArticulosDTO> actualizaArticulo(@PathVariable  Long id, @RequestBody ArticulosDTO dto) {
        
        ArticulosDTO updatedArticle = servicioArticulo.actualizaArticulo(id, dto);
        return ResponseEntity.ok(updatedArticle);

    }

    @DeleteMapping("/articulos/delete/{id}")
    public ResponseEntity<Void> borraArticulo(@PathVariable Long id){

        servicioArticulo.borraArticulo(id);
        return ResponseEntity.noContent().build();

    }
    

}
