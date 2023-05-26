package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Administrador;
import com.ProyectoEgg.EggProyectoServicios.entidades.Persona;
import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.repositorios.AdministradorRepositorio;
import com.ProyectoEgg.EggProyectoServicios.repositorios.ProveedorRepositorio;
import com.ProyectoEgg.EggProyectoServicios.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class PersonaServicio implements UserDetailsService{
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    ProveedorRepositorio proveedorRepositorio;
    
    @Autowired
    AdministradorRepositorio administradorRepositorio;
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         
        Optional <Usuario> respuesta = usuarioRepositorio.buscarPorEmail(email);
        
        Optional <Proveedor> respuesta2 = proveedorRepositorio.buscarPorEmail(email);
        
        Optional <Administrador> respuest3 = administradorRepositorio.buscarPorEmail(email);
        
        Persona persona = new Persona();
        
        if(respuesta.isPresent()){
            persona = respuesta.get();
        }else if(respuesta2.isPresent()){
            persona = respuesta2.get();
        }else if(respuest3.isPresent()){
            persona = respuest3.get();
        }else{
            persona = null;
        }
        
        if(persona != null){
            
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            
            /* Otorgamos permisos segun el rol y luego agregamos a la lista de permisos*/
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + persona.getRol().toString());
            
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession session = attr.getRequest().getSession(true);
            
            session.setAttribute("usuariosession", persona);
            
            return new User(persona.getEmail(), persona.getPassword(), permisos);
        }else{
            return null;
        }
    }
}
