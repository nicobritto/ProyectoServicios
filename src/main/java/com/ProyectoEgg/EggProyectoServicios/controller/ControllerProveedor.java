package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.entidades.Persona;
import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.entidades.Rubro;
import com.ProyectoEgg.EggProyectoServicios.entidades.Solicitud;
import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Estado;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioProveedor;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioRubro;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioSolicitud;
import com.ProyectoEgg.EggProyectoServicios.service.TrabajoServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/")
public class ControllerProveedor {
    
    @Autowired
    private ServicioProveedor servicioProveedor;
    @Autowired
    private ServicioRubro servicioRubro;
    
    @Autowired
    private ServicioSolicitud servicioSolicitud;
    
    @Autowired
    private TrabajoServicio servicioTrabajo;
    
    @GetMapping("/registrar")
    public String registrar(ModelMap model) {
        List<Rubro> rubros = servicioRubro.listarRubros();
        model.addAttribute("rubros", rubros);
        return "proveedorForm.html";
    }
        
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,@RequestParam String apellido,
            @RequestParam String email,@RequestParam String telefono,@RequestParam(required = false) String idRubro, 
            @RequestParam String password, @RequestParam String password2, @RequestParam(required = false) Float honorarios,
            MultipartFile archivo,ModelMap modelo){
        try {
            servicioProveedor.crearProveedor(nombre,apellido,email,telefono,idRubro, 
                    password, password2, honorarios, archivo);
            modelo.put("exito","Se registró correctamente!");
            return "index.html";
        }catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "proveedorForm.html";
        }
    }
    
    @GetMapping("/proveedores")
    public String mostrarTodos(ModelMap modelo){
        List<Proveedor> proveedores = servicioProveedor.listarTodos();
        
        modelo.addAttribute("proveedores", proveedores);
      
        return "servicios_todos.html";
    }
           //ok hasta aca
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
         List<Rubro> rubros = servicioRubro.listarRubros();
        modelo.addAttribute("rubros", rubros);
        modelo.put("proveedor", servicioProveedor.getOne(id));
        
        return "proveedorModificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String actualizacion(@PathVariable String id, @RequestParam String nombre,
            @RequestParam String apellido,@RequestParam String email,
            @RequestParam String telefono,@RequestParam(required = false)String idRubro,
            @RequestParam String password, @RequestParam String password2,
            @RequestParam(required = false)Float honorarios, MultipartFile archivo,ModelMap modelo){
        try {
            servicioProveedor.modificarProveedor(id, nombre,apellido,email,telefono,
                    idRubro, password, password2, honorarios, archivo);
            modelo.put("exito","Proveedor modificado con exito!");
            return "index.html";
        }catch (Exception e) {
             List<Rubro> rubros = servicioRubro.listarRubros();
             modelo.put("id",id);
             modelo.addAttribute("rubros", rubros);
             modelo.put("proveedor", servicioProveedor.getOne(id));
             modelo.put("error", e.getMessage());
        return "proveedorModificar.html";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap model) {

        try {
            servicioProveedor.eliminar(id);
            return "redirect:../proveedores";
        } catch (Exception ex) {
            //Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            model.put("error", ex.getMessage());
            return "redirect:../proveedores";
        }
    }
    
    //Hay que cambiar este controller
    @GetMapping("/buscarPorRubro/{nombre}")
    public String mostrarXrubro(ModelMap modelo , @PathVariable String nombre){
        List<Proveedor> proveedores = servicioProveedor.listarXrubro(nombre);
        
        modelo.addAttribute("proveedores", proveedores);
      
        // serviciosPlomeros retorna cualquier vista de proveedor 
        // borrar estos comentarios cuando vean si funca bien
            return "serviciosPlomeros.html";
        
    }

    @GetMapping("/masInfo/{id}")
    public String mostrarInfoProveedor(@PathVariable String id, ModelMap modelo){
        
        modelo.put("proveedor", servicioProveedor.getOne(id));
        
        return "masInfoProveedor.html";
    }
    
    @GetMapping("/notificaciones")
    public String mostrarNotificaciones(ModelMap modelo, HttpSession session){

        Persona logeado = (Persona) session.getAttribute("usuariosession");
        
        List<Solicitud> solicitudes = servicioSolicitud.listarSolicitudesTrueXProveedor(logeado.getId());
        
        modelo.put("solicitud", solicitudes);
        
        return "notificaciones.html";
    }
    
    //No funciona
    @PostMapping("/trabajo")
    public String crearTrabajo(HttpSession session, ModelMap modelo,@RequestParam String estado){
        
        Persona logeado = (Persona) session.getAttribute("usuariosession");
        
        
        
        Trabajo trabajo = servicioTrabajo.crearTrabajo(logeado.getId(), Estado.valueOf(estado));
        
        return "notificaciones.html";
    }
    
}
