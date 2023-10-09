package com.app.web.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class OrdenTrabajo { // Cambio Vehiculo por OrdenTrabajo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "criterioAceptacion", nullable = false, length = 50)
    private String criterioAceptacion = "";


	@ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    Servicio servicio;

    public OrdenTrabajo() {

    }
    public OrdenTrabajo(String criterioAceptacion) {
    super();
    this.criterioAceptacion = criterioAceptacion;
    } 
    
    public OrdenTrabajo(String criterioAceptacion, Cliente cliente, Vehiculo vehiculo, Servicio servicio) {
		super();
		this.criterioAceptacion = criterioAceptacion;
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.servicio = servicio;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
	public String getCriterioAceptacion() {
		return criterioAceptacion;
	}

	public void setCriterioAceptacion(String criterioAceptacion) {
		this.criterioAceptacion = criterioAceptacion;
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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    

	@Override
	public String toString() {
		return "OrdenTrabajo [id=" + id + ", criterioAceptacion=" + criterioAceptacion + ", cliente=" + cliente
				+ ", vehiculo=" + vehiculo + ", servicio=" + servicio + "]";
	}
}
