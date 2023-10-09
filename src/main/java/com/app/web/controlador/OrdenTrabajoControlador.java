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
import com.app.web.entidad.OrdenTrabajo; // Cambio Vehiculo por OrdenTrabajo
import com.app.web.entidad.Servicio;
import com.app.web.entidad.Vehiculo;
import com.app.web.services.OrdenTrabajoServicio; // Cambio Vehiculo por OrdenTrabajo
import com.app.web.services.VehiculoServiceImpl;
import com.app.web.services.ClienteServicioIMPL;
import com.app.web.services.ServicioServicioImpl;


@Controller
public class OrdenTrabajoControlador { // Cambio VehiculoControlador por OrdenTrabajoControlador

    @Autowired
    private OrdenTrabajoServicio servicio; // Cambio VehiculoService por OrdenTrabajoService

    @GetMapping("/ordentrabajo") // Cambio vehiculos por ordentrabajo
    public String listarOrdenesTrabajo(Model modelo) {
        modelo.addAttribute("ordentrabajo", servicio.listarTodosLosOrdenesTrabajo()); // Cambio vehiculos por ordentrabajo
        return "ordentrabajo"; // Nos retorna al archivo ordentrabajo
    }

    @Autowired
    private VehiculoServiceImpl vehiculoService;
    @Autowired
    private ClienteServicioIMPL clienteService;
    @Autowired
    private ServicioServicioImpl servicioService;


    @GetMapping("/ordentrabajo/new") // Cambio vehiculos por ordentrabajo
    public String mostrarFormularioRegistroOrdenTrabajo(Model modelo) { // Cambio mostrarFormularioRegistroVehiculo por mostrarFormularioRegistroOrdenTrabajo
        OrdenTrabajo ordentrabajo = new OrdenTrabajo(); // Cambio Vehiculo por OrdenTrabajo
        Cliente cliente = new Cliente();
        Vehiculo vehiculo = new Vehiculo();

        List<Servicio> servicios = servicioService.listarTodosLosServicios();
        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        List<Vehiculo> vehiculos = vehiculoService.listarTodosLosVehiculos();

        modelo.addAttribute("ordentrabajo", ordentrabajo); // Cambio vehiculo por ordentrabajo
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("servicios", servicios);

        return "crear_ordentrabajo"; // Cambio crear_vehiculo por crear_ordentrabajo
    }

    @PostMapping("/ordentrabajo") // Cambio vehiculos por ordentrabajo
    public String guardarOrdenTrabajo(@ModelAttribute("ordentrabajo") OrdenTrabajo ordentrabajo) { // Cambio vehiculo por ordentrabajo
        try {
            servicio.guardarOrdenTrabajo(ordentrabajo); // Cambio Vehiculo por OrdenTrabajo
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatePatenteException("La patente ya existe.");
        }
        return "redirect:/ordentrabajo"; // Cambio vehiculos por ordentrabajo

    }

    @GetMapping("/ordentrabajo/editar/{id}") // Cambio vehiculos por ordentrabajo
    public String mostrarFormEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("ordentrabajo", servicio.obtenerOrdenTrabajoPorID(id)); // Cambio vehiculo por ordentrabajo
        OrdenTrabajo ordentrabajo = servicio.obtenerOrdenTrabajoPorID(id);
        List<Servicio> servicios = servicioService.listarTodosLosServicios();
        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        List<Vehiculo> vehiculos = vehiculoService.listarTodosLosVehiculos();

        modelo.addAttribute("ordentrabajo", ordentrabajo);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("servicios", servicios);

        return "editar_ordentrabajo"; // Cambio editar_vehiculos por editar_ordentrabajo
    }

    @PostMapping("/ordentrabajo/{id}") // Cambio vehiculos por ordentrabajo
    public String actualizarOrdenTrabajo(@PathVariable Long id,
            @ModelAttribute("ordentrabajo") OrdenTrabajo ordentrabajo, Model modelo) { // Cambio vehiculo por ordentrabajo
        OrdenTrabajo ordentrabajoExistente = servicio.obtenerOrdenTrabajoPorID(id); // Cambio Vehiculo por OrdenTrabajo
        ordentrabajoExistente.setId(id);
        ordentrabajoExistente.setCriterioAceptacion(ordentrabajo.getCriterioAceptacion());

        servicio.actualizarOrdenTrabajo(ordentrabajoExistente); // Cambio Vehiculo por OrdenTrabajo
        return "redirect:/ordentrabajo"; // Cambio vehiculos por ordentrabajo
    }

    @GetMapping("/ordentrabajo/{id}") // Cambio vehiculos por ordentrabajo
    public String eliminarOrdenTrabajo(@PathVariable Long id) {
        servicio.eliminarOrdenTrabajo(id); // Cambio Vehiculo por OrdenTrabajo
        return "redirect:/ordentrabajo"; // Cambio vehiculos por ordentrabajo
    }
}
