package com.ProyectoEgg.EggProyectoServicios.service;

import com.ProyectoEgg.EggProyectoServicios.entidades.Rubro;
import com.ProyectoEgg.EggProyectoServicios.repositorios.RubroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRubro {
    
    @Autowired
    private RubroRepositorio rubroRepositorio;
    
    
    
    @Transactional
    public Rubro crearRubro(String nombre) throws Exception {
        validar(nombre);
        Rubro rubro=new Rubro(nombre);
        return rubroRepositorio.save(rubro);
    }
    
    
     @Transactional
     public void modificarRubro(String nombre,String id) throws Exception {
        validar(nombre);
        Optional<Rubro> rta = rubroRepositorio.findById(id);
   
        if (rta.isPresent()) {
           Rubro rubro=rta.get();
          rubro.setRubros(nombre);
          rubroRepositorio.save(rubro);
        }
    }
     
    @Transactional
    public void eliminarRubro(String id) throws Exception {
        rubroRepositorio.deleteById(id);
    }
    
      public Rubro getOne(String id){
        
        return rubroRepositorio.getOne(id);
    }

    
     //todavia no lo probe
     public List<Rubro> listarRubros() {
        List<Rubro> rubro = new ArrayList();
        rubro = rubroRepositorio.findAll();
        return rubro;
    }
     
    
    
     
     public void validar(String nombre) throws Exception {

        if (nombre.trim().isEmpty() || nombre == null || nombre.length()<5) {
            throw new Exception("el nombre no puede ser nulo ni menor a 5 caracteres");
        }
    }
    
    
    
}
