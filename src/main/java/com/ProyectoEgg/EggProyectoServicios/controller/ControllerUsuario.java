package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email,   @RequestParam String password, @RequestParam String password2, ModelMap modelo){

    try{

        servicioUsuario.crearUsuario(nombre,apellido,email, password,  password2);
        modelo.put("exito","usuario creado con exito");
        return "index.html";
        
    }catch (Exception e){
        modelo.put("error", e);
        return "usuarioForm.html";
    }

    }

    @GetMapping("/usuarios")
    public String mostrarTodos(ModelMap modelo){
        List<Usuario> usuarios = servicioUsuario.listarTodos();

        modelo.addAttribute("usuarios", usuarios);
        return "usuarios_todos.html";
    }

    @PostMapping("/modificar/{id}")
    public String actualizacion(@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido,
                                @RequestParam String email, @RequestParam String password, ModelMap modelo){

        try {
            servicioUsuario.modificarUsuario(id, nombre, apellido, email, password);
            modelo.put("Exito", "Usuario modificado con exito");
            return "index.html";
        }catch (Exception e){
            modelo.put("Error", "Error al modificar el usuario");
            return "usuarioModificar.html";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){

        try {
            servicioUsuario.eliminar(id);
            return "redirect:../usuarios";

        }catch (Exception e){
            modelo.put("Error", e.getMessage());
            return "redirect:../usuarios";
        }
    }



}
