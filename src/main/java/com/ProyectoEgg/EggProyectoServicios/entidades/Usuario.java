
package com.ProyectoEgg.EggProyectoServicios.entidades;

import javax.persistence.Entity;

@Entity
public class Usuario extends Persona {


    private Trabajo trabajo;

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email) {
        super(nombre, apellido, email);
    }
}
