
package com.ProyectoEgg.EggProyectoServicios.entidades;

import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Rol;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Usuario extends Persona {

    @OneToMany
    private List <Trabajo> trabajos;

    public Usuario() {
    }

    public Usuario(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public Usuario(List<Trabajo> trabajos, String id, String nombre, String apellido, String email, String password, Rol rol) {
        super(id, nombre, apellido, email, password, rol);
        this.trabajos = trabajos;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    
    

 
    

}