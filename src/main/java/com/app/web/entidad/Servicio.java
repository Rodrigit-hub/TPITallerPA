package com.app.web.entidad;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicios") // Cambio el nombre de la tabla a "servicios"

public class Servicio { // Cambio el nombre de la clase a "Servicio"
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
    
    

	@Column(name = "precio", nullable = false, length = 50)
    public BigDecimal precio;

    /*
    @OneToMany(mappedBy = "servicio")
    private List<OrdenTrabajo> ordentrabajo;
    */
    
    @ManyToMany(mappedBy = "servicios")
    private Set<OrdenTrabajo> ordenesTrabajo = new HashSet<>();
 
    public Servicio() {
        
    }

    public Servicio(Long id, String nombre, String descripcion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Servicio(String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}	
    //Agregamos un método para actualizar el precio
    public void actualizarPrecio(BigDecimal nuevoPrecio) {
        this.precio = nuevoPrecio;
    }

	public Set<OrdenTrabajo> getOrdenesTrabajo() {
		return ordenesTrabajo;
	}

	public void setOrdenesTrabajo(Set<OrdenTrabajo> ordenesTrabajo) {
		this.ordenesTrabajo = ordenesTrabajo;
	}

    @Override
    public String toString() {
        return "Servicio [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

}
