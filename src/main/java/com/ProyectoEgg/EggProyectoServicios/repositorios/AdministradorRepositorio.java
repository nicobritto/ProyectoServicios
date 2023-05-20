package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepositorio extends JpaRepository<Administrador, String>{
    
}
