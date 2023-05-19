package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
}
