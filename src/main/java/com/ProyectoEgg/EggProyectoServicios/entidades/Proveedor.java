
package com.ProyectoEgg.EggProyectoServicios.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Proveedor extends Persona{
    
    
    private Long telefono;
    private String rubro;
    private Float calificacion;
    
    @OneToMany
    private List <Trabajo> trabajos;
    
    @OneToOne
    private Imagen imagen;
    
    public Proveedor(){
    }

    public Proveedor(Long telefono, String rubro, Byte foto, Float calificacion, List<Trabajo> trabajos) {
        this.telefono = telefono;
        this.rubro = rubro;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
    }

    public Proveedor(Long telefono, String rubro, Byte foto, Float calificacion, List<Trabajo> trabajos, String nombre, String apellido, String email) {
        super(nombre, apellido, email);
        this.telefono = telefono;
        this.rubro = rubro;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
    }

    public Proveedor(Long telefono, String rubro, Byte foto, Float calificacion, List<Trabajo> trabajos, Imagen imagen) {
        this.telefono = telefono;
        this.rubro = rubro;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
        this.imagen = imagen;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
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

    
    
    
    
}
