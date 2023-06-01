package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Trabajo;
import com.ProyectoEgg.EggProyectoServicios.entidades.Voto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositorio extends JpaRepository<Voto, String>{
    
//    @Query("SELECT t FROM Trabajo t WHERE t.proveedor.id= :id AND t.estado LIKE 'ACEPTADO'")
//    public List<Trabajo> listarPorProveedor(@Param("id") String id);
}
