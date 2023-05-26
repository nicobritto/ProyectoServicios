package com.ProyectoEgg.EggProyectoServicios.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Voto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    
    private Integer puntaje;
    
    private String reseña;
    
    @OneToOne
    private Usuario usuario;
    
    @OneToOne
    private Trabajo trabajo;

    public Voto() {
    }

    public Voto(String id, Integer puntaje, String reseña, Usuario usuario, Trabajo trabajo) {
        this.id = id;
        this.puntaje = puntaje;
        this.reseña = reseña;
        this.usuario = usuario;
        this.trabajo = trabajo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
    
    
    
}
