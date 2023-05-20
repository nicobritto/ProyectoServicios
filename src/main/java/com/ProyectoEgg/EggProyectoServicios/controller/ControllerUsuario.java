package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    private ServicioUsuario servicioUsuario;


    @GetMapping("/registrar")
    public String registrar(){
        return "usuarioForm.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, ModelMap modelo){

    try{

        servicioUsuario.crearUsuario(nombre, apellido, email);
        modelo.put("exito","usuario creado con exito");
        return "index.html";
    }catch (Exception e){
        modelo.put("Error", e);
        return "usuarioForm.html";
    }

    }


}
