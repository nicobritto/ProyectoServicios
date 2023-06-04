package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
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
    

    public Voto crearVoto(Integer puntaje, String resenia, String idTrabajo) throws Exception{
        validar(puntaje,resenia);

  

        
        Voto voto = new Voto();
        
        Trabajo trabajo = trabajoServicio.getOne(idTrabajo);
        
        voto.setPuntaje(puntaje);
        voto.setReseña(resenia);
        voto.setTrabajo(trabajo);
        voto.setUsuario(null);
  
        
        return votoRepositorio.save(voto);

    }
    
    public void validar(Integer puntaje, String resenia) throws Exception {
        if (puntaje == null || puntaje > 5 || puntaje < 1) {
            throw new Exception("El puntaje no puede estar vacío y debe ser un número entre 1 y 5");
        } 
        if (resenia.trim().isEmpty()) {
            throw new Exception("Reseña no puede estar vacía");
        }
    }
    
    
}
