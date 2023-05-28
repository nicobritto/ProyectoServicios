package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Solicitud;
import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.repositorios.SolicitudRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioSolicitud {
    
    @Autowired
    SolicitudRepositorio solicitudRepositorio;
    
    @Autowired
    ServicioUsuario usuarioServicio;
    
    @Autowired
    ServicioProveedor proveedorServicio;
    
    @Transactional
    public Solicitud crearSolicitud(String idUsuario, String idProveedor){
        
        Solicitud solicitud = new Solicitud();
        
        Usuario usuario = usuarioServicio.getOne(idUsuario);
        Proveedor proveedor = proveedorServicio.getOne(idProveedor);
        
        solicitud.setUsuario(usuario);
        solicitud.setProveedor(proveedor);
        solicitud.setEstado(Boolean.TRUE);
        solicitud.setFecha(new Date());
        
        solicitudRepositorio.save(solicitud);
        
        return solicitud;
    }
    
    @Transactional
    public void modificarEstado(String id, Boolean estado){
        
        Optional<Solicitud> respuesta = solicitudRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Solicitud solicitud = respuesta.get();
            
            solicitud.setEstado(Boolean.FALSE);
            
            solicitudRepositorio.save(solicitud);
 
        }
    }
    
    public List<Solicitud> listarSolicitudesTrueXProveedor(String idProveedor){
        
        List<Solicitud> solicitudes = solicitudRepositorio.buscarSolicitud(idProveedor);
        
        return solicitudes;
        
    }
    
     public Solicitud getOne(String id){
        
        return solicitudRepositorio.getOne(id);
    }
}
