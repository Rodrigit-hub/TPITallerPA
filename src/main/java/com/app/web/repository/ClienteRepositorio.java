package com.app.web.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Cliente;

/**
 * Interfaz que define un repositorio JPA para la entidad Cliente.
 *
 * La interfaz extiende JpaRepository para aprovechar las capacidades de Spring Data JPA
 * para realizar operaciones CRUD en la entidad Cliente.
 */

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	@Query("SELECT c FROM Cliente c WHERE " +
		       "(:nombre IS NULL OR c.nombre LIKE %:nombre%) AND " +
		       "(:id IS NULL OR CAST(c.id AS string) LIKE %:id%) AND " +
		       "(:apellido IS NULL OR c.apellido LIKE %:apellido%) AND " +
		       "(:email IS NULL OR c.email LIKE %:email%) AND " +
		       "(:telefono IS NULL OR c.telefono LIKE %:telefono%) AND " +
		       "(:direccion IS NULL OR c.direccion LIKE %:direccion%) AND " +
		       "(:informacion IS NULL OR c.informacion LIKE %:informacion%) AND " +
		       "(:fechaCreacionOrden IS NULL OR DATE_FORMAT(c.fechaCreacionOrden, '%Y-%m-%d') = DATE_FORMAT(:fechaCreacionOrden, '%Y-%m-%d'))")
		List<Cliente> findByClienteContainingIgnoreCase(
		       @Param("nombre") String nombre, 
		       @Param("id") String id, 
		       @Param("apellido") String apellido, 
		       @Param("email") String email, 
		       @Param("telefono") String telefono, 
		       @Param("direccion") String direccion, 
		       @Param("informacion") String informacion,
		       @Param("fechaCreacionOrden") Date fechaCreacionOrden);
}



	/** No se necesitan métodos adicionales aquí, ya que JpaRepository proporciona
    *automáticamente métodos para realizar operaciones CRUD en la entidad Cliente.
    * Estos métodos incluyen findAll(), findById(), save(), deleteById(), etc.
	**/