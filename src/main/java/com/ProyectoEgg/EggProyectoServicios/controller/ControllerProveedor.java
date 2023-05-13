package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioProveedor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/Proveedor")//localhost:8080/autor
public class ControllerProveedor {
    
     @Autowired
    private ServicioProveedor servicioProveedor;
     
      @GetMapping("/registrar")//localhost:8080/autor/registrar
        public String registrar() {
            
            return "index.html";
        }
        
        
        @PostMapping("/registro")
        public String registro(String nombre,float calificacion,String email,long telefono,  ModelMap modelo){
            try {
                servicioProveedor.crearProveedor(nombre,calificacion,email,telefono);
                 return "proveedorForm.html";
            } catch (Exception e) {
                 modelo.put("error", e.getMessage());
                 return "proveedorForm.html";
            }
                
        
        }
    
           

    
}
