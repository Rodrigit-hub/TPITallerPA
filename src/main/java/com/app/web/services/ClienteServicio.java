package com.app.web.services;
import java.util.Date;
import java.util.List;

import com.app.web.entidad.Cliente;
import com.app.web.entidad.Vehiculo;

/**
 * Interfaz que define los métodos para gestionar clientes en la aplicación.
 */


public interface ClienteServicio {
	
	public List<Cliente> listarTodosLosClientes(); //Recupera una lista de todos los clientes registrados en la aplicación.
	public Cliente guardarCliente(Cliente cliente); //Guarda un nuevo cliente en la base de datos.
	public Cliente obtenerClientePorId(Long id); //Obtiene un cliente por su ID.
	public Cliente actualizarCliente(Cliente cliente); //Actualiza los datos de un cliente existente en la base de datos.
	public void eliminarCliente(Long id); //Elimina un cliente de la base de datos por su ID.
	public List<Cliente> listAll(String palabraClave);
	public List<Cliente> findByClienteContainingIgnoreCase(String nombre, String id, String apellido, String email,
			String telefono, String direccion, String informacion, Date fechaCreacionOrden);
    public List<Cliente> listarTodosLosClientesNoEliminados();

    public List<Cliente> listarTodosLosClientesEliminados();
    
    public void restaurarCliente(Long id);
    	
}
