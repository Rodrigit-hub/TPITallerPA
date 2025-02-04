package com.app.web.entidad;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class OrdenTrabajo { // Cambio Vehiculo por OrdenTrabajo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_cierre")
    private Date fechaCierre; // Nuevo campo para la fecha de cierre
    
    @Column(name = "eliminado")
    private boolean eliminado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    Vehiculo vehiculo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "orden_trabajo_servicio",
        joinColumns = @JoinColumn(name = "ordentrabajo_id"),
        inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private Set<Servicio> servicios = new HashSet<>();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadosOrden estado;
    
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    tecnico tecnico;
    
    public OrdenTrabajo() {

    }

    public Set<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(Set<Servicio> servicios) {
        this.servicios = servicios;
    }

    public OrdenTrabajo(Cliente cliente, Vehiculo vehiculo, Servicio servicio, tecnico tecnico) {
        super();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.tecnico = tecnico;
        this.fechaCreacion = new Date(); // Establecer la fecha de creación al momento de la creación de la orden de trabajo
    }
    
    public OrdenTrabajo(Cliente cliente, Vehiculo vehiculo, tecnico tecnico) {
        super();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.tecnico = tecnico;
        this.fechaCreacion = new Date();
        this.servicios = new HashSet<>(); // Inicializar la colección
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    // Getters y Setters para las relaciones

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    public EstadosOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadosOrden estado) {
        this.estado = estado;
    }
    
    public tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(tecnico tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public String toString() {
        return "OrdenTrabajo [id=" + id + ", cliente=" + cliente
                + ", vehiculo=" + vehiculo + "]";
    }
}
