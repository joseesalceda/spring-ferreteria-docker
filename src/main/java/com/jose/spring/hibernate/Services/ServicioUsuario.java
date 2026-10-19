package com.jose.spring.hibernate.Services;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jose.spring.hibernate.Repositorios.IUsuarios;

@Service 
public class ServicioUsuario implements UserDetailsService{

    final private IUsuarios repoUsuarios;

    public ServicioUsuario(IUsuarios repUsuarios){

        this.repoUsuarios = repUsuarios;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        var usuario = repoUsuarios.findByUser(username)
                            .orElseThrow(()-> new UsernameNotFoundException("Usuario, no encontrado: " + username));

        return new User(usuario.getUser(), usuario.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())));
        
    }

    



}
