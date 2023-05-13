package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.service.ServicioProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")//localhost:8080/autor
public class ControllerProveedor {
    
     @Autowired
    private ServicioProveedor servicioProveedor;
     
      @GetMapping("/registrar")//localhost:8080/autor/registrar
        public String registrar() {
            
            return "proveedorForm.html";
        }
        
        @PostMapping("/registro")
        public String registro(String nombre,String apellido,String email,String telefono,String rubro,ModelMap modelo){
            try {
                servicioProveedor.crearProveedor(nombre,apellido,email,telefono,rubro);
                 return "index.html";
            } catch (Exception e) {
                 modelo.put("error", e.getMessage());
            }
                return "proveedorForm.html";
        }
    
           

    
}
