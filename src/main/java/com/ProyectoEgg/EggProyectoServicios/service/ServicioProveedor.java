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
    public void crearProveedor(String nombre,String apellido,String email,String telefono,String rubro ) throws Exception  {
        validar(nombre,email,telefono);
        List<Trabajo> trabajos=null;
        Imagen imagen=new Imagen();
        
        Proveedor proveedor =new Proveedor();
        proveedor.setApellido(apellido);
        proveedor.setCalificacion(null);
        proveedor.setEmail(email);
        proveedor.setImagen(null);
        proveedor.setTelefono(telefono);
        proveedor.setTrabajos(trabajos);
        proveedor.setRubro(rubro);
        proveedor.setNombre(nombre);
        
        proveedorRepositorio.save(proveedor);
        
    }
    
    public void validar(String nombre,String email,String telefono ) throws Exception {
      
        if (nombre.trim().isEmpty()) {
            throw new Exception("nombre no puede ser nulo");
        }
       
        if (email.trim().isEmpty()) {
            throw new Exception("email no puede ser nulo");
        }
        if (telefono.trim().isEmpty()) {
            throw new Exception("telefono no puede ser nulo");
        }
        

    }

    
        

    
    
    
}
