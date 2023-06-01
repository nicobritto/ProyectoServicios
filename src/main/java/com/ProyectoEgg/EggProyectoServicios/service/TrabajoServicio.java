package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.entidades.Voto;
import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Estado;
import com.ProyectoEgg.EggProyectoServicios.excepciones.MiException;
import com.ProyectoEgg.EggProyectoServicios.repositorios.ProveedorRepositorio;
import com.ProyectoEgg.EggProyectoServicios.repositorios.TrabajoRepositorio;
import com.ProyectoEgg.EggProyectoServicios.repositorios.UsuarioRepositorio;
import com.ProyectoEgg.EggProyectoServicios.repositorios.VotoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajoServicio {
    
    @Autowired
    private TrabajoRepositorio trabajoRepositorio;
    
    @Autowired
    private ServicioProveedor servicioProveedor;
    
    @Autowired
    private ServicioUsuario servicioUsuario;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    
    @Autowired
    private VotoRepositorio votoRepositorio;
    
    @Transactional
    public Trabajo crearTrabajo(String idProveedor, Estado estado, String idUsuario){
        
        Proveedor proveedor = servicioProveedor.getOne(idProveedor);
        Usuario usuario = servicioUsuario.getOne(idUsuario);
        
        List<Trabajo> trabajos = new ArrayList();

        Trabajo trabajo = new Trabajo();
        
        trabajo.setEstado(estado);
        trabajo.setProveedor(proveedor);
        trabajo.setUsuario(usuario);
        trabajo.setVoto(null);
        
        trabajos.add(trabajo);
        
        proveedor.setTrabajos(trabajos);
        usuario.setTrabajos(trabajos);
        
//        usuarioRepositorio.save(usuario);
//        proveedorRepositorio.save(proveedor);
        
        trabajoRepositorio.save(trabajo);
        
        return trabajo;
        
    }
    
    @Transactional
    public void modificarEstado(String id, Estado estado){
        Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Trabajo trabajo = respuesta.get();
            
            trabajo.setEstado(estado);
            
            trabajoRepositorio.save(trabajo);
        }
    }
    
    public List<Trabajo> trabajosXProveedor(String idProveedor){
        
        List<Trabajo> trabajos = trabajoRepositorio.listarPorProveedor(idProveedor);
        
        return trabajos;
    }
    
    public List<Trabajo> trabajosXUsuario(String idUsuario){
        
        List<Trabajo> trabajos = trabajoRepositorio.listarPorUsuario(idUsuario);
        
        return trabajos;
    }
    
    public Trabajo getOne(String id){
        
        return trabajoRepositorio.getOne(id);
    }
    
    public Trabajo buscarTrabajoPorIdProvedoor(String idProve)throws Exception{
        
        Optional<Trabajo> trabajo=trabajoRepositorio.buscarTrabajoPorIdPRoveedor(idProve);
        if (trabajo.isPresent()) {
            Trabajo trabajito=trabajo.get();
            return trabajito;
        }
         throw new MiException("trabajo por id de proveedor no encotnradoasd");
        
    }
    
    @Transactional
    public void modificarVoto(String id, String idVoto){
        
        Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Optional<Voto> respuesta2 = votoRepositorio.findById(idVoto);
            
            if(respuesta2.isPresent()){
                
                Trabajo trabajo = respuesta.get();
                
                Voto voto = respuesta2.get();
                
                trabajo.setVoto(voto);
                
                trabajoRepositorio.save(trabajo);
            }
        }
    }
    
}
