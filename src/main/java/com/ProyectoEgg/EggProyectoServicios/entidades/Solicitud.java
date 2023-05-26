package com.ProyectoEgg.EggProyectoServicios.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Solicitud {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    
    private Boolean estado; //True:en espera
    
    @OneToOne
    private Usuario usuario;
    
    @OneToOne
    private Proveedor proveedor;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Solicitud() {
    }

    public Solicitud(String id, Boolean estado, Usuario usuario, Proveedor proveedor, Date fecha) {
        this.id = id;
        this.estado = estado;
        this.usuario = usuario;
        this.proveedor = proveedor;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
