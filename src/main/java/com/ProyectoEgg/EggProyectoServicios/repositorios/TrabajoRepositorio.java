package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepositorio extends JpaRepository<Trabajo, String>{
    
    
 @Query("SELECT t FROM Trabajo t WHERE t.proveedor.id= :id")
    public Optional <Trabajo> buscarTrabajoPorIdPRoveedor(@Param("id") String id);
    
 @Query("SELECT t FROM Trabajo t WHERE t.proveedor.id= :id AND t.estado LIKE 'ACEPTADO'")
    public List<Trabajo> listarPorProveedor(@Param("id") String id); 
    
}
