package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.entidades.Voto;
import com.ProyectoEgg.EggProyectoServicios.repositorios.VotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioVoto {
    
    @Autowired
    private VotoRepositorio votoRepositorio;
    
    @Autowired
    private TrabajoServicio trabajoServicio;
    
    public Voto crearVoto(Integer puntaje, String resenia, String idTrabajo){
        
        Voto voto = new Voto();
        
        Trabajo trabajo = trabajoServicio.getOne(idTrabajo);
        
        voto.setPuntaje(puntaje);
        voto.setReseña(resenia);
        voto.setTrabajo(trabajo);
        voto.setUsuario(null);
        
        return votoRepositorio.save(voto);

    }
    
    
}
