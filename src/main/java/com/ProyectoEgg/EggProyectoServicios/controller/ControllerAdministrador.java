package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioAdministrador;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/administrador")
public class ControllerAdministrador {
    
    @Autowired
    ServicioAdministrador servicioAdmin;
    
    @Autowired
    ServicioUsuario servicioUsuario;
    
//    @GetMapping("/dashboard")
//    public String panelAdministrativo(ModelMap modelo){
//        
//        return "Admin.html";
//    }
    
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
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        
        modelo.put("administrador", servicioAdmin.getOne(id));

        return "AdminModificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String actualizacion(@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido,
                                @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo){

        try {
            servicioAdmin.modificarAdmin(id, nombre, apellido, email, password, password2);
            modelo.put("exito", "Administrador modificado con exito");
            return "index.html";
        }catch (Exception e){
            modelo.put("error", "Error al modificar el administrador");
            return "AdminModificar.html";
        }
    }
    
    @GetMapping("/usuarios")
    public String administrarUsuarios(ModelMap modelo){
        
        List<Usuario> usuarios = servicioUsuario.listarTodos();
         
        modelo.addAttribute("usuarios", usuarios);
        
        return "Admin_usuarios_todos.html";
    }
}
