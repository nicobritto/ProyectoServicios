package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Imagen;
import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.repositorios.ProveedorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioProveedor {
    
    @Autowired
     private ProveedorRepositorio proveedorRepositorio;
     
    @Autowired 
     private ImagenServicio imagenServicio;
    
    @Transactional  
    public void crearProveedor(String nombre,String apellido,String email,String telefono,String rubro,MultipartFile archivo ) throws Exception  {
        validar(nombre,email,telefono);
        List<Trabajo> trabajos=null;
    
        Imagen imagen=imagenServicio.guardar(archivo);
        
        Proveedor proveedor =new Proveedor();
        proveedor.setApellido(apellido);
        proveedor.setCalificacion(null);
        proveedor.setEmail(email);
        proveedor.setImagen(imagen);
        proveedor.setTelefono(telefono);
        proveedor.setTrabajos(trabajos);
        proveedor.setRubro(rubro);
        proveedor.setNombre(nombre);
        
        proveedorRepositorio.save(proveedor);
        
    }
    
    @Transactional(readOnly = true)
    public List<Proveedor> listarTodos(){
        
        List<Proveedor> proveedores = new ArrayList();
        
        proveedores = proveedorRepositorio.findAll();
        
        return proveedores;
    }
    
    @Transactional
    public void modificarProveedor(String id, String nombre,String apellido,String email,String telefono,String rubro, MultipartFile archivo) throws Exception{
       
       validar(nombre,email,telefono);
        
       Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
       
       if(respuesta.isPresent()){
           
           Proveedor proveedor = respuesta.get();
           Imagen imagen=imagenServicio.guardar(archivo);
           
           proveedor.setNombre(nombre);
           proveedor.setApellido(apellido);
           proveedor.setEmail(email);
           proveedor.setTelefono(telefono);
           proveedor.setRubro(rubro.toLowerCase());
           proveedor.setImagen(imagen);
           
           proveedorRepositorio.save(proveedor);
       }
    }
    
    public Proveedor getOne(String id){
        
        return proveedorRepositorio.getOne(id);
    }
    
    public void eliminar(String id){
        
        Proveedor noticia = proveedorRepositorio.getById(id);
        
        proveedorRepositorio.delete(noticia);
        
    }
     
    public List<Proveedor> listarXrubro(String rubro){
        
        List<Proveedor> proveedores = new ArrayList();
        
        proveedores = proveedorRepositorio.buscarPorRubros(rubro);
        
        return proveedores;
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
