package com.app.web.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.repository.ClienteRepositorio;
import com.app.web.entidad.Cliente;
import com.app.web.entidad.Vehiculo;


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
	
	public List<Cliente> listarTodosLosClientesNoEliminados() {
        return repositorioCliente.findByEliminadoFalse();
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
		//repositorio.deleteById(id);
		Cliente cliente = repositorioCliente.findById(id).orElse(null);
        if (cliente != null) {
        	cliente.setEliminado(true);
        	repositorioCliente.save(cliente);
        }
	}	
	@Override
    public List<Cliente> listarTodosLosClientesEliminados() {
        return repositorioCliente.findByEliminadoTrue();
    }
	
    public void restaurarCliente(Long id) {
    	Cliente cliente = repositorioCliente.findById(id).orElse(null);
	    if (cliente != null) {
	    	cliente.setEliminado(false);
	    	repositorioCliente.save(cliente);
	    }
    }
	
	@Override
	public List<Cliente> findByClienteContainingIgnoreCase(
	        String nombre, String id, String apellido, String email, String telefono, 
	        String direccion, String informacion, Date fechaCreacionOrden) {
	    return repositorioCliente.findByClienteContainingIgnoreCase(
	        nombre, id, apellido, email, telefono, direccion, informacion, fechaCreacionOrden);
	}
	@Override
	
	public List<Cliente> listAll(String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}
}
