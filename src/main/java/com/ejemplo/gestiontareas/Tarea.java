package com.ejemplo.gestiontareas;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;

    @Temporal(TemporalType.DATE)
    private Date fechaLimite;

    public Tarea() {}

    public Tarea(String nombre, EstadoTarea estado, Date fechaLimite) {
        this.nombre = nombre;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public boolean estaProximaALaFechaLimite() {
        long diff = fechaLimite.getTime() - new Date().getTime();
        return diff < (24 * 60 * 60 * 1000); // Avisa si falta menos de un día
    }
}
