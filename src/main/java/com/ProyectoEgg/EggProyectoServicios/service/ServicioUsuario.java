package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Rol;
import com.ProyectoEgg.EggProyectoServicios.excepciones.MiException;
import com.ProyectoEgg.EggProyectoServicios.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
public class ServicioUsuario {


    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearUsuario(String nombre,String apellido,String email,String password, String password2) throws Exception{
        
        validar(nombre,apellido,email, password, password2);
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setRol(Rol.USER);
        usuario.setPassword(password);
        
        usuarioRepositorio.save(usuario);
    }
    
    
    
     public Usuario getOne(String id){
        
        return usuarioRepositorio.getOne(id);
    }
     
    
    public void eliminar(String id){
        
        Usuario usuario = usuarioRepositorio.getById(id);
        
        usuarioRepositorio.delete(usuario);
        
    }
    @Transactional
    public List<Usuario> listarTodos(){

        List<Usuario> usuarios = new ArrayList<>();

        usuarios = usuarioRepositorio.findAll();

        return usuarios;

    }

    public void modificarUsuario(String id, String nombre, String apellido,String email, String password, String password2) throws Exception{
        validar( nombre, apellido,  email,  password, password2);
        Optional<Usuario> respuesta= usuarioRepositorio.findById(id);

        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setPassword(new BCryptPasswordEncoder().encode(password));


            usuarioRepositorio.save(usuario);
        }
    }

    
    public void validar(String nombre,String apellido,String email,String password,String password2) throws MiException {
      
        if (nombre.trim().isEmpty()) {
            throw new MiException("Nombre no puede ser nulo");
        }
        if (apellido.trim().isEmpty()) {
            throw new MiException("Apellido no puede ser nulo");
        }       
        if (email.trim().isEmpty()) {
            throw new MiException("Email no puede ser nulo");
        }
      
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
         
          }
    
}
