package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Imagen;
import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.repositorios.ProveedorRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioProveedor {
    
    @Autowired
     ProveedorRepositorio proveedorRepositorio;
    
        
    @Transactional  
    public void crearProveedor(String nombre,float calificacion,String email,long telefono ) throws Exception  {
        validar(nombre,email,telefono);
        List<Trabajo> trabajos=null;
        Imagen imagen=new Imagen();
       
        Proveedor proveerdor =new Proveedor();
        proveerdor.setApellido(nombre);
        proveerdor.setCalificacion(calificacion);
        proveerdor.setEmail(email);
        proveerdor.setImagen(imagen);
        proveerdor.setTelefono(telefono);
        proveerdor.setTrabajos(trabajos);
        
        proveedorRepositorio.save(proveerdor);
        
    }
    
    public void validar(String nombre,String email,long telefono ) throws Exception {
      
        if (nombre.trim().isEmpty()) {
            throw new Exception("nombre no puede ser nulo");
        }
       
        if (email.trim().isEmpty()) {
            throw new Exception("email no puede ser nulo");
        }
        if (telefono  > 1000) {
            throw new Exception("telefono no puede ser nulo");
        }
        

    }

    
        

    
    
    
}
