package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.service.ServicioAdministrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/administrador")
public class ControllerAdministrador {
    
    @Autowired
    ServicioAdministrador servicioAdmin;
    
    @GetMapping("/registrar")
    public String registrarAdmin(){
        return "AdminForm.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,@RequestParam String apellido,
            @RequestParam String email, @RequestParam String password, 
            @RequestParam String password2, ModelMap modelo){
        try {
            servicioAdmin.crearAdmin(nombre, apellido, email, password, password2);
            modelo.put("exito","Se registró correctamente :D ");
            return "index.html";
        }catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "AdminForm.html";
        }
    }
}
