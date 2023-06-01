package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.entidades.Persona;
import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import com.ProyectoEgg.EggProyectoServicios.entidades.Voto;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioUsuario;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioVoto;
import com.ProyectoEgg.EggProyectoServicios.service.TrabajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    private ServicioUsuario servicioUsuario;
    
    @Autowired
    private TrabajoServicio servicioTrabajo;

    @Autowired
    private ServicioVoto votoServicio;
    
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
                                @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo){

        try {
            servicioUsuario.modificarUsuario(id, nombre, apellido, email, password, password2);
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

    @GetMapping("/contratados")
    public String mostrarTrabajosContratados(ModelMap modelo, HttpSession session){
        
        Persona logeado = (Persona) session.getAttribute("usuariosession");
        
        List<Trabajo> trabajos = servicioTrabajo.trabajosXUsuario(logeado.getId());
        
        System.out.println(trabajos);
        
        modelo.put("trabajos", trabajos);
        
        return "trabajosContratadosUsuario.html";
    }

    @GetMapping("/calificacion/{id}")
    public String calificarServicio(@PathVariable String id, ModelMap modelo, HttpSession session){
        
        Trabajo trabajo = servicioTrabajo.getOne(id);
        
        modelo.put("trabajo", trabajo);
   
        return "calificacion.html";
    }
    
    @PostMapping("/calificado/{id}")
    public String calificado(@RequestParam Integer puntaje,@RequestParam String resenia,
           @PathVariable String id, ModelMap modelo, HttpSession session){
        
        Voto voto = votoServicio.crearVoto(puntaje, resenia, id);
        
        modelo.put("voto", voto);
        
        System.out.println(puntaje);
        System.out.println(resenia);
        System.out.println(id);
        servicioTrabajo.modificarVoto(id, voto.getId());
        
        Persona logeado = (Persona) session.getAttribute("usuariosession");
        
        List<Trabajo> trabajos = servicioTrabajo.trabajosXUsuario(logeado.getId());
        
        System.out.println(trabajos);
        
        modelo.put("trabajos", trabajos);
        
        return "redirect:../contratados";
    }
}
