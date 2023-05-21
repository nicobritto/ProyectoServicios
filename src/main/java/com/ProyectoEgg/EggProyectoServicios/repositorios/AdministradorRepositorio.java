package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Administrador;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministradorRepositorio extends JpaRepository<Administrador, String>{
    
    @Query("SELECT a FROM Administrador a WHERE a.email = :email")
    public Optional <Administrador> buscarPorEmail(@Param("email") String email);
}
