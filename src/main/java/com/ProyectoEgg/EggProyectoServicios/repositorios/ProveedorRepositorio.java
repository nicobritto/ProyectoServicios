package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String>{
    
  
    @Query("SELECT p FROM Proveedor p WHERE p.rubro.rubros LIKE :rubro")
    public  List<Proveedor> buscarPorRubros(@Param("rubro") String rubro);
    
    @Query("SELECT p FROM Proveedor p WHERE p.email = :email")
    public Optional <Proveedor> buscarPorEmail(@Param("email") String email);
}
