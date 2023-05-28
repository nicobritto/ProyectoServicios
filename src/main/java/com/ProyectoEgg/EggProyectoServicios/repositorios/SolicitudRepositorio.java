package com.ProyectoEgg.EggProyectoServicios.repositorios;

import com.ProyectoEgg.EggProyectoServicios.entidades.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepositorio extends JpaRepository<Solicitud, String>{
    
    
    @Query("SELECT s FROM Solicitud s WHERE s.proveedor.id = :id AND s.estado IS 1")
    public  List<Solicitud> buscarSolicitud(@Param("id") String id);
    
}
