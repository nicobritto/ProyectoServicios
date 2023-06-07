
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
    private Boolean baja;
    
    @OneToMany
    private List <Trabajo> trabajos;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Imagen imagen;
    
    private Float honorarios;
    
    private String descripcionTrabajo;
    
    public Proveedor(){
    }

    public Proveedor(String telefono, Rubro rubro, Float calificacion, List<Trabajo> trabajos, Imagen imagen, Float honorarios, String descripcionTrabajo) {
        this.telefono = telefono;
        this.rubro = rubro;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
        this.imagen = imagen;
        this.honorarios = honorarios;
        this.descripcionTrabajo = descripcionTrabajo;
    }


    public Proveedor(String telefono, Rubro rubro, Float calificacion, List<Trabajo> trabajos, Imagen imagen, Float honorarios, String id, String nombre, String apellido, String email, String password, Rol rol, String descripcionTrabajo) {
        super(id, nombre, apellido, email, password, rol);
        this.telefono = telefono;
        this.rubro = rubro;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
        this.imagen = imagen;
        this.honorarios = honorarios;
        this.descripcionTrabajo = descripcionTrabajo;
       
    }

    public Boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
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

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    
    
    
    
}
