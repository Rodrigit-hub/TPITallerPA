package com.app.web.entidad;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity // Indica que esta clase es una entidad JPA
@Table(name="clientes")  // Especifica el nombre de la tabla en la base de datos.
public class Cliente {
	@Id // Indica que el campo 'id' es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática de valores para 'id'.
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 50) // Configura la columna 'nombre' en la tabla.
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 50)// Configura la columna 'apellido'.
	private String apellido;
	
	@Column(name = "email", nullable = false, length = 50, unique = true) // Configura la columna 'email'.
	private String email;
	
	@OneToMany(mappedBy = "cliente")
    private List<Vehiculo> vehiculos;

	
	// Constructores
	public Cliente() {
		 // Constructor por defecto necesario para JPA.
	}
	public Cliente( String nombre, String apellido, String email) {
		// Constructor para crear un nuevo cliente sin ID.
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public Cliente(Long id, String nombre, String apellido, String email) {
		 // Constructor para crear un cliente con ID (generalmente se utiliza cuando se recupera desde la base de datos).
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
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

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}
	
	
	
}



