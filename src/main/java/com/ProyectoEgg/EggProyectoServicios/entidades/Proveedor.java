
package com.ProyectoEgg.EggProyectoServicios.entidades;

import java.util.List;


public class Proveedor extends Persona{
    
    private Long telefono;
    private String rubro;
    private Byte foto;
    private Float calificacion;
    private List <Trabajo> trabajos;

    public Proveedor() {
    }

    public Proveedor(Long telefono, String rubro, Byte foto, Float calificacion, List<Trabajo> trabajos) {
        this.telefono = telefono;
        this.rubro = rubro;
        this.foto = foto;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
    }

    public Proveedor(Long telefono, String rubro, Byte foto, Float calificacion, List<Trabajo> trabajos, String nombre, String apellido, String email) {
        super(nombre, apellido, email);
        this.telefono = telefono;
        this.rubro = rubro;
        this.foto = foto;
        this.calificacion = calificacion;
        this.trabajos = trabajos;
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

    public Byte getFoto() {
        return foto;
    }

    public void setFoto(Byte foto) {
        this.foto = foto;
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
