package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Imagen;
import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Rubro;
import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Rol;
import com.ProyectoEgg.EggProyectoServicios.excepciones.MiException;
import com.ProyectoEgg.EggProyectoServicios.repositorios.ProveedorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioProveedor {
    
    @Autowired
     private ProveedorRepositorio proveedorRepositorio;
     
    @Autowired 
     private ImagenServicio imagenServicio;
    @Autowired
    private ServicioRubro servicioRubro;
    
    @Transactional  
    public void crearProveedor(String nombre,String apellido,String email,
            String telefono,String idRubro, String password, String password2, 
            Float honorarios, String descripcionTrabajo, MultipartFile archivo ) throws Exception  {
        
        validar(nombre,apellido,email,telefono,password,password2,honorarios,descripcionTrabajo,archivo);
        
        List<Trabajo> trabajos=null;
        Rubro rubro=servicioRubro.getOne(idRubro);
    
        Imagen imagen=imagenServicio.guardar(archivo);
        
        Proveedor proveedor =new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.setApellido(apellido);
        proveedor.setCalificacion(null);
        proveedor.setEmail(email);
        proveedor.setImagen(imagen);
        proveedor.setTelefono(telefono);
        proveedor.setTrabajos(trabajos);
        proveedor.setRubro(rubro);
        proveedor.setPassword(new BCryptPasswordEncoder().encode(password));
        proveedor.setRol(Rol.PROVEEDOR);
        proveedor.setHonorarios(honorarios);
        proveedor.setDescripcionTrabajo(descripcionTrabajo);
        proveedor.setBaja(Boolean.FALSE);
        
        proveedorRepositorio.save(proveedor);
        
    }
    
    @Transactional(readOnly = true)
    public List<Proveedor> listarTodos(){
        
        List<Proveedor> proveedores = new ArrayList();
         List<Proveedor> proveedores2 = new ArrayList();
        
        proveedores = proveedorRepositorio.findAll();
        for (Proveedor proveedore : proveedores) {
            if (proveedore.getBaja()==false) {
                proveedores2.add(proveedore);
            }
        }
        
        return proveedores2;
    }
    
    @Transactional
    public void modificarProveedor(String id, String nombre,String apellido,
            String email,String telefono,String idRubro, String password, 
            String password2, Float honorarios,  String descripcionTrabajo, 
            MultipartFile archivo) throws Exception{
       
       validar(nombre,apellido,email,telefono,password,password2,honorarios,descripcionTrabajo,archivo);
        Rubro rubro=servicioRubro.getOne(idRubro);
       Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
       
       
       
       if(respuesta.isPresent()){
           
           
           Proveedor proveedor = respuesta.get();  
           
            String idImagen = null;
            if (proveedor.getImagen() != null) {
                idImagen = proveedor.getImagen().getId();
            }
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);

            proveedor.setImagen(imagen);
           
          
           proveedor.setNombre(nombre);
           proveedor.setApellido(apellido);
           proveedor.setEmail(email);
           proveedor.setTelefono(telefono);
           proveedor.setRubro(rubro);
          
           proveedor.setPassword(new BCryptPasswordEncoder().encode(password));
           proveedor.setRol(Rol.PROVEEDOR);
           proveedor.setHonorarios(honorarios);
           proveedor.setDescripcionTrabajo(descripcionTrabajo);
           
           proveedorRepositorio.save(proveedor);
       }
    }
    
    public Proveedor getOne(String id){
        
        return proveedorRepositorio.getOne(id);
    }
    
    @Transactional
    public void eliminar(String id){
        
        Proveedor proveedor = proveedorRepositorio.getById(id);
        
        proveedor.setBaja(Boolean.TRUE);
        proveedorRepositorio.save(proveedor);
        
    }

    public List<Proveedor> listarXrubro(String rubro){
        
        List<Proveedor> proveedores = new ArrayList();
        
        proveedores = proveedorRepositorio.buscarPorRubros(rubro);
        
         List<Proveedor> proveedores2 = new ArrayList();
        
        proveedores = proveedorRepositorio.findAll();
        for (Proveedor proveedore : proveedores) {
            if (proveedore.getBaja()==false) {
                proveedores2.add(proveedore);
            }
        }
        
        return proveedores2;
    }

    
    public void validar(String nombre,String apellido,String email,String telefono,String password, String password2,Float honorarios, String descripcionTrabajo, 
            MultipartFile archivo) throws MiException {
      
        if (nombre.trim().isEmpty()) {
            throw new MiException("Nombre no puede ser nulo");
        }
        if (apellido.trim().isEmpty()) {
            throw new MiException("Apellido no puede ser nulo");
        }       
        if (email.trim().isEmpty()) {
            throw new MiException("Email no puede ser nulo");
        }
        if (telefono.trim().isEmpty() || telefono.length() < 8) {
            throw new MiException("Telefono no puede ser nulo o faltan caracteres");
        }
        
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
         if ( honorarios==null || honorarios<1 ) {
            throw new MiException("Honorarios no puede estar vacía");
        }
        if (descripcionTrabajo.trim().isEmpty()) {
            throw new MiException("Ingrese una descripción del trabajo");
        }
        if (archivo == null) {
            throw new MiException("Ingrese un archivo");
        }
         
         
          }

    
}
