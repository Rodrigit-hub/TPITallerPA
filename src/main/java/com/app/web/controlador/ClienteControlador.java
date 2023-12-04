package com.app.web.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entidad.Cliente;
import com.app.web.entidad.OrdenTrabajo;
import com.app.web.services.ClienteServicio;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

@Controller
public class ClienteControlador {
	
	@Autowired
	private ClienteServicio servicio;
	// Maneja solicitudes GET para listar todos los clientes.
	@GetMapping("/clientes")
	public String listarClientes(Model modelo) {
		// Llama al servicio para obtener todos los clientes
		modelo.addAttribute("clientes",servicio.listarTodosLosClientes());
		// Devuelve la vista "clientes" que mostrará la lista de clientes.
		List<Cliente> clientes = servicio.listarTodosLosClientes();
	    // Agregar la lógica para obtener la fecha de la última orden directamente en el controlador
	    for (Cliente cliente : clientes) {
	        if (cliente.getOrdenesDeTrabajo() != null && !cliente.getOrdenesDeTrabajo().isEmpty()) {
	            // Ordenar las órdenes de trabajo por fecha de creación en orden descendente
	            cliente.getOrdenesDeTrabajo().sort(Comparator.comparing(OrdenTrabajo::getFechaCreacion).reversed());

	            // Obtener la fecha de la orden más reciente
	            Date fechaUltimaOrden = cliente.getOrdenesDeTrabajo().get(0).getFechaCreacion();
	            cliente.actualizarFechaCreacionOrden(fechaUltimaOrden);
	        }
	    }
	    
	    modelo.addAttribute("clientes", clientes);
		return "clientes"; //nos retorna al archivo 
		
	}
	 // Maneja solicitudes GET para mostrar el formulario de registro de cliente.
	@GetMapping("/clientes/nuevo")
	public String mostrarFormularioDeRegistroDeCliente(Model modelo) {
		  // Crea un nuevo objeto Cliente.
		Cliente cliente = new Cliente();
		// Agrega el objeto cliente al modelo para ser utilizado en la vista.
		modelo.addAttribute("cliente", cliente);
		 // Devuelve la vista "crear_cliente" que contiene el formulario de registro.
		return "crear_cliente";
		
	}
	// Maneja solicitudes POST para guardar un nuevo cliente.
	@PostMapping("/clientes")
	public String guardarCliente(@ModelAttribute("cliente")Cliente cliente) {
		// Llama al servicio para guardar el nuevo cliente en la base de datos.
		servicio.guardarCliente(cliente);
		// Redirige al usuario de nuevo a la lista de clientes después de guardar.
		return "redirect:/clientes";
	}
	 // Maneja solicitudes GET para mostrar el formulario de edición de cliente.
	@GetMapping("/clientes/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo){
		 // Llama al servicio para obtener el cliente con el ID especificado.
		modelo.addAttribute("cliente", servicio.obtenerClientePorId(id));
		 // Devuelve la vista "editar_cliente" que contiene el formulario de edición.
		return "editar_cliente";
	}
	 // Maneja solicitudes POST para actualizar un cliente existente.
	@PostMapping("/clientes/{id}")
	public String actualizarCliente(@PathVariable Long id, @ModelAttribute("cliente")Cliente cliente){
		// Llama al servicio para obtener el cliente existente con el ID especificado.
		Cliente clienteExistente = servicio.obtenerClientePorId(id);
		// Actualiza los datos del cliente existente con los datos del formulario.
		clienteExistente.setId(id);
		clienteExistente.setNombre(cliente.getNombre());
		clienteExistente.setApellido(cliente.getApellido());
		clienteExistente.setEmail(cliente.getEmail());
		// Llama al servicio para actualizar el cliente en la base de datos.
		servicio.actualizarCliente(clienteExistente);
		// Redirige al usuario de nuevo a la lista de clientes después de actualizar.
		return "redirect:/clientes";
		
	}
	
	// Maneja solicitudes GET para buscar clientes por palabra clave.
	@GetMapping("/clientes/buscar")
	public String buscarClientesPorPalabraClave(@Param("palabraClave") String palabraClave, Model modelo) {
		List<Cliente> listaClientes = servicio.listAll(palabraClave);
	    modelo.addAttribute("clientes", listaClientes);
	    modelo.addAttribute("palabraClave", palabraClave);
	    return "clientes";
	}
	
	// Maneja solicitudes GET para eliminar un cliente.
	@GetMapping("/clientes/{id}")
	public String eliminarCliente(@PathVariable Long id){
		// Llama al servicio para eliminar el cliente con el ID especificado.
		servicio.eliminarCliente(id);
		// Redirige al usuario de nuevo a la lista de clientes después de eliminar.
		return "redirect:/clientes";
	}
	
}