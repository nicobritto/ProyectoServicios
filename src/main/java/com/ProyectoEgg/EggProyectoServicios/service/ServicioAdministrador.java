package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Administrador;
import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Rol;
import com.ProyectoEgg.EggProyectoServicios.excepciones.MiException;
import com.ProyectoEgg.EggProyectoServicios.repositorios.AdministradorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioAdministrador {
    
    @Autowired
    AdministradorRepositorio adminRepositorio;
    
    @Transactional  
    public void crearAdmin(String nombre,String apellido,String email,
            String password, String password2) throws Exception  {
        
        validar(nombre,apellido,email,password,password2);
        
        Administrador admin =new Administrador();
        
        admin.setNombre(nombre);
        admin.setApellido(apellido);
        admin.setEmail(email);
        admin.setPassword(new BCryptPasswordEncoder().encode(password));
        admin.setRol(Rol.ADMIN);
        
        
        adminRepositorio.save(admin);
        
    }
    
    public Administrador getOne(String id){
        
        return adminRepositorio.getOne(id);
    }
    
    public void modificarAdmin(String id, String nombre, String apellido,String email, String password, String password2) throws Exception{
        validar( nombre, apellido,  email,  password, password2);
        Optional<Administrador> respuesta= adminRepositorio.findById(id);

        if(respuesta.isPresent()){
            Administrador admin = respuesta.get();

            admin.setNombre(nombre);
            admin.setApellido(apellido);
            admin.setEmail(email);
            admin.setPassword(new BCryptPasswordEncoder().encode(password));


            adminRepositorio.save(admin);
        }
    }
    
    public void validar(String nombre,String apellido,String email,String password, String password2) throws MiException {
      
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
