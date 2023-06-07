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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalController {
    
    @Autowired
    private ServicioProveedor servicioProveedor;
    @Autowired
    private ServicioRubro servicioRubro;
    
    @GetMapping("/")
      public String asd(ModelMap modelo){
          
        List<Proveedor> proveedores = servicioProveedor.listarTodosActivos();
        List<Rubro> rubros = servicioRubro.listarRubros();
        
        modelo.addAttribute("rubros", rubros);
        modelo.addAttribute("proveedores", proveedores);
      
          
          return "index.html";
    }
    
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if(error != null){
            
            modelo.put("error", "Usuario o contraseña incorrectos");
        }
        return "loginUsuario.html";
    }
}
