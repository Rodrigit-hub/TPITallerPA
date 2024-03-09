package com.app.web.entidad;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "clientes") // Especifica el nombre de la tabla en la base de datos.
public class Cliente {
	@Id // Indica que el campo 'id' es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática de valores para 'id'.
	private Long id;

	@Column(name = "nombre", nullable = false, length = 50) // Configura la columna 'nombre' en la tabla.
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 50) // Configura la columna 'apellido'.
	private String apellido;

	@Column(name = "email", nullable = false, length = 50, unique = true) // Configura la columna 'email'.
	private String email;

	@Column(name = "telefono", nullable = false, length = 12) // Configura la columna 'telefono'.
	private String telefono;

	@Column(name = "direccion", nullable = false, length = 50) // Configura la columna 'direccion'.
	private String direccion;

	@Column(name = "informacion", nullable = false, length = 50) // Configura la columna 'informacion'.
	private String informacion = "";
	
    @Column(name = "eliminado")
    private boolean eliminado;

	@OneToMany(mappedBy = "cliente")
	private List<Vehiculo> vehiculos;
	
    @OneToMany(mappedBy = "cliente")
    private List<OrdenTrabajo> ordenesDeTrabajo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion_orden")
    private Date fechaCreacionOrden;


	// Constructores
	public Cliente() {
		// Constructor por defecto necesario para JPA.
	}

	public Cliente(String nombre, String apellido, String email, String telefono, String direccion, String informacion) {
		// Constructor para crear un nuevo cliente sin ID.
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.informacion = informacion;
	}

	public Cliente(Long id, String nombre, String apellido, String email, String telefono, String direccion,
			String informacion) {
		// Constructor para crear un cliente con ID (generalmente se utiliza cuando se
		// recupera desde la base de datos).
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.informacion = informacion;
	}

	// Métodos getters y setters para 'id', 'nombre', 'apellido' y 'email'.
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}
	
    public List<OrdenTrabajo> getOrdenesDeTrabajo() {
        return ordenesDeTrabajo;
    }

    public void setOrdenesDeTrabajo(List<OrdenTrabajo> ordenesDeTrabajo) {
        this.ordenesDeTrabajo = ordenesDeTrabajo;
    }
    
    public Date getFechaCreacionOrden() {
        return fechaCreacionOrden;
    }

    public void actualizarFechaCreacionOrden(Date fechaCreacionOrden) {
	    if (fechaCreacionOrden != null && (this.fechaCreacionOrden == null || fechaCreacionOrden.after(this.fechaCreacionOrden))) {
	        this.fechaCreacionOrden = fechaCreacionOrden;
	    }
    }


	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

}
