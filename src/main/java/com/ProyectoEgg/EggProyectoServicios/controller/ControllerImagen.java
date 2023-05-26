package com.ProyectoEgg.EggProyectoServicios.controller;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import com.ProyectoEgg.EggProyectoServicios.service.ServicioProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ControllerImagen {
    
    @Autowired
    ServicioProveedor proveedorServicio;
    
    @GetMapping("/perfil/{id}") //Este get mapping trae una cadena de bytes que es la imagen del id especificado
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id){
        
        Proveedor proveedor = proveedorServicio.getOne(id);
        
        byte[] imagen = proveedor.getImagen().getContenido();
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}
