package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.service.ServicioProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



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
        public String registro(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String email,@RequestParam String telefono,@RequestParam String rubro, MultipartFile archivo,ModelMap modelo){
            try {
                servicioProveedor.crearProveedor(nombre,apellido,email,telefono,rubro,archivo);
                  modelo.put("exito"," todo fue un exito :D ");
                 return "index.html";
            } catch (Exception e) {
                 modelo.put("error", e.getMessage());
                   return "proveedorForm.html";
            }
              
        }
    
           

    
}
