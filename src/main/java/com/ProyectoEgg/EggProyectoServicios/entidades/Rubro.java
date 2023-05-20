package com.ProyectoEgg.EggProyectoServicios.entidades;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Rubro  {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
   
    private String id;
    private String rubros;

    public Rubro() {
    }

    public Rubro(String id, String rubros) {
        this.id = id;
        this.rubros = rubros;
    }

    public Rubro(String rubros) {
        this.rubros = rubros;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRubros() {
        return rubros;
    }

    public void setRubros(String rubros) {
        this.rubros = rubros;
    }

    
    
}
