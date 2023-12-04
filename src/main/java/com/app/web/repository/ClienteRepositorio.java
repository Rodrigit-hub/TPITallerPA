package com.app.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Cliente;

/**
 * Interfaz que define un repositorio JPA para la entidad Cliente.
 *
 * La interfaz extiende JpaRepository para aprovechar las capacidades de Spring Data JPA
 * para realizar operaciones CRUD en la entidad Cliente.
 */

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE "
	        + "c.nombre LIKE %?1% OR "
	        + "c.id LIKE %?1% OR "
	        + "c.apellido LIKE %?1% OR "
	        + "c.email LIKE %?1% OR "
	        + "c.telefono LIKE %?1% OR "
	        + "c.direccion LIKE %?1% OR "
	        + "c.informacion LIKE %?1%")

	

	List<Cliente> findByClienteContainingIgnoreCase(String palabraClave);	
	
	
	/** No se necesitan métodos adicionales aquí, ya que JpaRepository proporciona
    *automáticamente métodos para realizar operaciones CRUD en la entidad Cliente.
    * Estos métodos incluyen findAll(), findById(), save(), deleteById(), etc.
	**/
}