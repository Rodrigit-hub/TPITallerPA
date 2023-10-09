package com.app.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entidad.Servicio;
import com.app.web.services.ServicioServicio;


@Controller
public class ServicioControlador {
	
	@Autowired
	private ServicioServicio servicioMecanico;
	
	@GetMapping("/servicios")
	public String listarServicios(Model modelo) {
		modelo.addAttribute("servicios", servicioMecanico.listarTodosLosServicios());
		return "servicios"; // Nos retorna al archivo servicios
	}
	
	@GetMapping("/servicios/nuevo")
	public String crearServicioFormula(Model modelo) {
		Servicio servicio1 = new Servicio();
		modelo.addAttribute("servicio", servicio1);
		return "crear_servicio";
	}
	
	@PostMapping("/servicios")
	public String guardarServicio(@ModelAttribute("servicio") Servicio servicio) {
		servicioMecanico.guardarServicio(servicio);
		return "redirect:/servicios";
	}

	@GetMapping("/servicios/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("servicio", servicioMecanico.obtenerServicioPorId(id));
		return "editar_servicio";
	}
	
	@PostMapping("/servicios/{id}")
	public String actualizarServicio(@PathVariable Long id, @ModelAttribute("servicio") Servicio servicio) {
		Servicio servicioExistente = servicioMecanico.obtenerServicioPorId(id);
		servicioExistente.setId(id);
		servicioExistente.setNombre(servicio.getNombre());
		servicioExistente.setDescripcion(servicio.getDescripcion());
		
		servicioMecanico.actualizarServicio(servicioExistente);
		return "redirect:/servicios";
	}
	
	@GetMapping("/servicios/{id}")
	public String eliminarServicio(@PathVariable Long id) {
		servicioMecanico.eliminarServicio(id);
		return "redirect:/servicios";
	}
}
