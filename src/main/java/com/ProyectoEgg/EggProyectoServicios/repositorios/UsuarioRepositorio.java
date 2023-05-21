package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    List<Usuario> buscarPorId(@Param("id") String id);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Optional <Usuario> buscarPorEmail(@Param("email") String email);
}
