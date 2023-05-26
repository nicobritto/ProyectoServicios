package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Estado;
import com.ProyectoEgg.EggProyectoServicios.excepciones.MiException;
import com.ProyectoEgg.EggProyectoServicios.repositorios.TrabajoRepositorio;
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
    
    
    @Transactional
    public Trabajo crearTrabajo(String idProveedor, Estado estado){
        
        Proveedor proveedor = servicioProveedor.getOne(idProveedor);
        
        Trabajo trabajo = new Trabajo();
        
        trabajo.setEstado(estado);
        trabajo.setProveedor(proveedor);
        trabajo.setVoto(null);
        
        trabajoRepositorio.save(trabajo);
        
        return trabajo;
        
    }
    
    public Trabajo buscarTrabajoPorIdProvedoor(String idProve)throws Exception{
        
        Optional<Trabajo> trabajo=trabajoRepositorio.buscarTrabajoPorIdPRoveedor(idProve);
        if (trabajo.isPresent()) {
            Trabajo trabajito=trabajo.get();
            return trabajito;
        }
         throw new MiException("trabajo por id de proveedor no encotnradoasd");
        
    }
    
}
