package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Rubro;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioProveedor;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioRubro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/rubro")
public class ControllerRubro {
    
    @Autowired
    private ServicioRubro servicioRubro;
     
    @GetMapping("/registrarRubro")
    public String registrar() {
        return "rubroForm.html";
    }
    
    
    
     @PostMapping("/guardar")
    public String registro(@RequestParam String nombre,ModelMap modelo){
        try {
            servicioRubro.crearRubro(nombre);
            modelo.put("exito"," todo fue un exito :D ");
            return "redirect:../rubro/listaRubros";
        }catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "rubroForm.html";
        }
    }
    
    
        @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap model) {

        try {
            servicioRubro.eliminarRubro(id);
             return "redirect:../listaRubros";
           
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
              return "redirect:../listaRubros";
        }
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("rubro", servicioRubro.getOne(id));
        return "rubroEditar.html";
    }
    
     @PostMapping("/modificar/{id}")
    public String actualizar(@RequestParam String rubros,@PathVariable String id,ModelMap model){
        try {
            servicioRubro.modificarRubro( rubros,id);
            model.put("exito"," todo fue un exito :D ");
            return "redirect:../listaRubros";
        }catch (Exception e) {
             model.put("error", e.getMessage());
            
            return "redirect:../listaRubros";
        }
    }
    
      @GetMapping("/listaRubros")
    public String mostrarTodos(ModelMap modelo){
        
        List<Rubro> rubros = servicioRubro.listarRubros();
        
        modelo.addAttribute("rubros", rubros);
      
        return "todoslosRubros.html";
    }
    
    
    
    
    
}
