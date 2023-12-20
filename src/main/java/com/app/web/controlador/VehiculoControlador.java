package com.app.web.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entidad.Cliente;
import com.app.web.entidad.Marca;
import com.app.web.entidad.Modelo;
import com.app.web.entidad.Vehiculo;
import com.app.web.services.MarcaService;
import com.app.web.services.MarcaServiceImpl;
import com.app.web.services.VehiculoService;
import com.app.web.services.ClienteServicio;
import com.app.web.services.ClienteServicioIMPL;
import com.app.web.services.ModeloServicio;
import com.app.web.services.ModeloServicioImpl;

@Controller
public class VehiculoControlador {

	@Autowired
	private VehiculoService servicio;

	@GetMapping("/vehiculos")
	public String listarVehiculos(Model modelo) {
		modelo.addAttribute("vehiculos", servicio.listarTodosLosVehiculos());
		return "vehiculos"; // Nos retorna al archivo vehiculos
	}
	
	@Autowired
	private MarcaServiceImpl marcaService;
	@Autowired
	private ClienteServicioIMPL clienteService;
	@Autowired
	private ModeloServicioImpl modeloService;
	
	@GetMapping("/vehiculos/new")
	public String mostrarFormularioRegistroVehiculo(Model modelo) {
		Vehiculo vehiculo = new Vehiculo();
		Marca marca = new Marca();
		Cliente cliente = new Cliente();
		Modelo modeloAuto = new Modelo();
		
        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        List<Marca> marcas = marcaService.listarTodasLasMarcas();
        List<Modelo> modelos = modeloService.listarTodosLosModelos();
		
		modelo.addAttribute("vehiculo", vehiculo);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("marcas", marcas);
        modelo.addAttribute("modelos", modelos);
		return "crear_vehiculo";
	}

	@PostMapping("/vehiculos")
	public String guardarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
		try {
		servicio.guardarVehiculo(vehiculo);}
		catch (DataIntegrityViolationException e) {
            throw new DuplicatePatenteException("La patente ya existe.");
		}
		return "redirect:/vehiculos";

	}

	@GetMapping("/vehiculos/editar/{id}")
	public String mostrarFormEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("vehiculo", servicio.obtenerVehiculoPorID(id));

	    // Obtener las listas necesarias para poblar los select en el formulario
	    List<Cliente> clientes = clienteService.listarTodosLosClientes();
	    List<Marca> marcas = marcaService.listarTodasLasMarcas();
	    List<Modelo> modelos = modeloService.listarTodosLosModelos();

	    // Agregar objetos necesarios al modelo;
	    modelo.addAttribute("clientes", clientes);
	    modelo.addAttribute("marcas", marcas);
	    modelo.addAttribute("modelos", modelos);

		return "editar_vehiculos";
	}

	@PostMapping("/vehiculos/{id}")
	public String actualizarVehiculo(@PathVariable Long id, @ModelAttribute("vehiculo") Vehiculo vehiculo,
			Model modelo) {
		Vehiculo vehiculoExistente = servicio.obtenerVehiculoPorID(id);
		vehiculoExistente.setId(id);
		vehiculoExistente.setPatente(vehiculo.getPatente());
		 // Actualizar la relación con Marca
	    vehiculoExistente.setMarca(vehiculo.getMarca());
	    
	    // Actualizar la relación con Modelo
	    vehiculoExistente.setModelo(vehiculo.getModelo());
	    
	    // Actualizar la relación con Cliente
	    vehiculoExistente.setCliente(vehiculo.getCliente());
		servicio.actualizarVehiculo(vehiculoExistente);
		return "redirect:/vehiculos";
	}

	@GetMapping("/vehiculos/{id}")
	public String eliminarVehiculo(@PathVariable Long id) {
		servicio.eliminarVehiculo(id);
		return "redirect:/vehiculos";
	}

}
