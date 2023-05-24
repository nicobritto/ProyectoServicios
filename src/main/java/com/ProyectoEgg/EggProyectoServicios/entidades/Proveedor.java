
package com.ProyectoEgg.EggProyectoServicios.entidades;

import com.ProyectoEgg.EggProyectoServicios.enumeraciones.Rol;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Proveedor extends Persona{
    
    
    private String telefono;
    @ManyToOne
    private Rubro rubro;
    private Float calificacion;
    
    @OneToMany
    private List <Trabajo> trabajos;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Imagen imagen;
    
    private Float honorarios;
    
    public Proveedor(){
    }

    public Proveedor(String telefono, Rubro rubro, Float calificacion, List<Trabajo> trabajos, Imagen imagen, Float honorarios) {
        this.telefono = telefono;
        this.rubro = rubro;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
        this.imagen = imagen;
        this.honorarios = honorarios;
    }

    public Proveedor(String telefono, Rubro rubro, Float calificacion, List<Trabajo> trabajos, Imagen imagen, Float honorarios, String id, String nombre, String apellido, String email, String password, Rol rol) {
        super(id, nombre, apellido, email, password, rol);
        this.telefono = telefono;
        this.rubro = rubro;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
        this.imagen = imagen;
        this.honorarios = honorarios;
        
       
    }
  
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public Float getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(Float honorarios) {
        this.honorarios = honorarios;
    }

    
    
    
    
}
