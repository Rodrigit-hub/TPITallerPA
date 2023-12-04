package com.app.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.repository.ClienteRepositorio;
import com.app.web.entidad.Cliente;


/**
 * Implementación de la interfaz ClienteServicio.
 */

@Service
public class ClienteServicioIMPL implements ClienteServicio{

	@Autowired 
	private ClienteRepositorio repositorioCliente;
	@Override
	public List<Cliente> listarTodosLosClientes() { //Recupera una lista de todos los clientes registrados en la aplicación.
		return repositorioCliente.findAll();
	}
	@Override
	public Cliente guardarCliente(Cliente cliente) { //Guarda un nuevo cliente en la base de datos. Retorna el cliente guardado con sus datos actualizados
		return repositorioCliente.save(cliente);
	}
	@Override
	public Cliente obtenerClientePorId(Long id) { //Obtiene un cliente por su ID único. Retorna el cliente correspondiente al ID proporcionado, o null si no se encuentra.
		return repositorioCliente.findById(id).get(); 
	}
	@Override
	public Cliente actualizarCliente(Cliente cliente) {  //Actualiza los datos de un cliente existente en la base de datos.
		return repositorioCliente.save(cliente);
	}
	@Override
	public void eliminarCliente(Long id) { //Elimina un cliente de la base de datos por su ID único.
		repositorioCliente.deleteById(id);
		
	}
	
	@Override
	public List<Cliente> listAll(String palabraClave) {
	    // Implementación de búsqueda por palabra clave
	    if (palabraClave == null || palabraClave.isEmpty()) {
	        // Si la palabra clave es nula o vacía, devuelve todos los clientes
	        return repositorioCliente.findAll();
	    } else {
	        // Si hay una palabra clave, realiza la búsqueda por palabra clave
	        return repositorioCliente.findByClienteContainingIgnoreCase(palabraClave);
	    }
	}
}
