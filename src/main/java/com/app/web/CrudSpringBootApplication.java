package com.app.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.web.entidad.Cliente;
import com.app.web.entidad.Marca;
import com.app.web.entidad.Modelo;
import com.app.web.entidad.OrdenTrabajo;
import com.app.web.entidad.Servicio;
import com.app.web.entidad.Vehiculo;
import com.app.web.entidad.tecnico;
import com.app.web.repository.ClienteRepositorio;
import com.app.web.repository.MarcaRepository;
import com.app.web.repository.ModeloRepositorio;
import com.app.web.repository.OrdenTrabajoRepositorio;
import com.app.web.repository.ServicioRepositorio;
import com.app.web.repository.VehiculoRepository;
import com.app.web.repository.tecnicoRepositorio;

/**
 * Clase principal de la aplicación Spring Boot.
 * Implementa la interfaz CommandLineRunner para ejecutar tareas al iniciar la aplicación.
 */
@SpringBootApplication
public class CrudSpringBootApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootApplication.class, args); //Inicia la aplicación Spring Boot.
	}

	@Autowired
    private MarcaRepository repositorioMarca;
    
	@Autowired
	private VehiculoRepository repositorio;
	
	@Autowired 
	private tecnicoRepositorio repositorioTecnico;
	
	@Autowired 
	private ClienteRepositorio repositorioCliente;
	
	@Autowired
	private ModeloRepositorio repositorioModelo;

	@Autowired
	private ServicioRepositorio repositorioServicio;
	
	@Autowired
	private OrdenTrabajoRepositorio repositorioOrdenTrabajo;
	
	
	//este método es ejecutado al iniciar la aplicación.
    //carga de datos iniciales.
	@Override
	public void run(String... args) throws Exception {
/*
		Servicio servicio1 = new Servicio("Cambio de aceite", "Cambia el aceite de un auto");
		servicio1.setPrecio(new BigDecimal("350.0"));
		repositorioServicio.save(servicio1);
		
		Servicio servicio2 = new Servicio("Mecanica ligera", "Cambiar amortiguador");
		servicio2.setPrecio(new BigDecimal("310.0"));
		repositorioServicio.save(servicio2);
		
		Servicio servicio3 = new Servicio("Reemplazo de frenos", "Cambio de pastillas y discos de freno");
		servicio3.setPrecio(new BigDecimal("130.0"));
		repositorioServicio.save(servicio3);
		
		Servicio servicio4 = new Servicio("Alineación y balanceo", "Ajuste de la alineación y balanceo de ruedas");
		servicio4.setPrecio(new BigDecimal("55.0"));
		repositorioServicio.save(servicio4);
		
		Servicio servicio5 = new Servicio("Cambio de bujías", "Reemplazo de bujías y revisión del sistema de encendido");
		servicio5.setPrecio(new BigDecimal("50.0"));
		repositorioServicio.save(servicio5);
		
		Servicio servicio6 = new Servicio("Diagnóstico de motor", "Análisis y diagnóstico de problemas del motor");
		servicio6.setPrecio(new BigDecimal("180.0"));
		repositorioServicio.save(servicio6);
		
		Servicio servicio7 = new Servicio("Cambio de filtro de aire", "Sustitución del filtro de aire del motor");
		servicio7.setPrecio(new BigDecimal("100.0"));
		repositorioServicio.save(servicio7);
		
		Servicio servicio8 = new Servicio("Recarga de aire acondicionado", "Recarga del sistema de aire acondicionado");
		servicio8.setPrecio(new BigDecimal("501.0"));
		repositorioServicio.save(servicio8);
		
		Servicio servicio9 = new Servicio("Cambio de correa de distribución", "Reemplazo de la correa de distribución");
		servicio9.setPrecio(new BigDecimal("510.0"));
		repositorioServicio.save(servicio9);
		
		Servicio servicio10 = new Servicio("Inspección de frenos", "Revisión y diagnóstico del sistema de frenos");
		servicio10.setPrecio(new BigDecimal("150.0"));
		repositorioServicio.save(servicio10);
		
		
		
        Marca marca1 = new Marca("Fiat");
        repositorioMarca.save(marca1);

        Marca marca2 = new Marca("Peugeot");
        repositorioMarca.save(marca2);
        
		Marca marca3 = new Marca("Renault");
		repositorioMarca.save(marca3);
		
		Marca marca4 = new Marca("Mazda");
		repositorioMarca.save(marca4);
		
		Marca marca5 = new Marca("Subaru");
		repositorioMarca.save(marca5);
		
		Marca marca6 = new Marca("Kia");
		repositorioMarca.save(marca6);
		
		Marca marca7 = new Marca("Jaguar");
		repositorioMarca.save(marca7);
		
		Marca marca8 = new Marca("Land Rover");
		repositorioMarca.save(marca8);
		
		Marca marca9 = new Marca("Chrysler");
		repositorioMarca.save(marca9);
		
		Marca marca10 = new Marca("Volvo");
		repositorioMarca.save(marca10);
		
		tecnico tecnico1 = new tecnico("Pineda","Alvaro",3385688 );	
		repositorioTecnico.save(tecnico1);
	
		tecnico tecnico2 = new tecnico("Pepe","Salamaleco",3585688 );	
		repositorioTecnico.save(tecnico2);
		
		Cliente cliente = new Cliente("Celia","Mabel","celia69@example.com", "154250048", "Santa Fe 120", "DNI" );
		repositorioCliente.save(cliente);
		
		Cliente cliente1 = new Cliente("Juan", "Perez", "juan123@example.com", "123456789", "Calle A 123", "DNI");
		repositorioCliente.save(cliente1);

		Cliente cliente2 = new Cliente("Ana", "Gomez", "ana456@example.com", "987654321", "Calle B 456", "Cédula");
		repositorioCliente.save(cliente2);

		Cliente cliente3 = new Cliente("Celia", "Leila", "celia232@example.com", "154250048", "Santa Fe 120", "Pasaporte");
		repositorioCliente.save(cliente3);

		Cliente cliente4 = new Cliente("Luis", "Rodriguez", "luis777@example.com", "789012345", "Calle C 789", "DNI");
		repositorioCliente.save(cliente4);

		Cliente cliente5 = new Cliente("María", "Lopez", "maria555@example.com", "567890123", "Calle D 987", "Cédula");
		repositorioCliente.save(cliente5);
		
		Cliente cliente6 = new Cliente("Laura", "Pedrol", "ana432@example.com", "987654321", "Calle C 232", "DNI");
		repositorioCliente.save(cliente6);
		
		Cliente cliente7 = new Cliente("Carlos", "Martinez", "carlos789@example.com", "456789012", "Calle C 789", "DNI");
		repositorioCliente.save(cliente7);
		
		Cliente cliente8 = new Cliente("Laura", "Rodriguez", "laura012@example.com", "321654987", "Calle D 012", "DNI");
		repositorioCliente.save(cliente8);
		
		Cliente cliente9 = new Cliente("Pedro", "Gonzalez", "pedro345@example.com", "789012345", "Calle E 345", "DNI");
		repositorioCliente.save(cliente9);


		Modelo modelo1 = new Modelo("Focus", "2001");
		repositorioModelo.save(modelo1);
		
		Modelo modelo2 = new Modelo("K", "2003");
		repositorioModelo.save(modelo2);
		
		Modelo modelo3 = new Modelo("Fiesta", "2009");
		repositorioModelo.save(modelo3);
		
		Modelo modelo4 = new Modelo("Civic", "2010");
		modelo4.setNombre("Civic");
		repositorioModelo.save(modelo4);
		
		Modelo modelo5 = new Modelo("Accord", "2012");
		modelo5.setNombre("Accord");
		repositorioModelo.save(modelo5);
		
		Modelo modelo6 = new Modelo("CR-V", "2015");
		modelo6.setNombre("CR-V");
		repositorioModelo.save(modelo6);
		
		Modelo modelo7 = new Modelo("Pilot", "2018");
		modelo7.setNombre("Pilot");
		repositorioModelo.save(modelo7);
		
		Modelo modelo8 = new Modelo("Odyssey", "2020");
		modelo8.setNombre("Odyssey");
		repositorioModelo.save(modelo8);
		
		Modelo modelo9 = new Modelo("Ridgeline", "2022");
		modelo9.setNombre("Ridgeline");
		repositorioModelo.save(modelo9);
		
		Vehiculo vehiculo1 = new Vehiculo("A033NFB");
		vehiculo1.setCliente(cliente2);
		vehiculo1.setMarca(marca3);
		vehiculo1.setModelo(modelo4);
		repositorio.save(vehiculo1);
		
		Vehiculo vehiculo2 = new Vehiculo("A332FDA");
		vehiculo2.setCliente(cliente3);
		vehiculo2.setMarca(marca5);
		vehiculo2.setModelo(modelo1);
		repositorio.save(vehiculo2);
		
		Vehiculo vehiculo3 = new Vehiculo("A123DAS");
		vehiculo3.setCliente(cliente1);
		vehiculo3.setMarca(marca3);
		vehiculo3.setModelo(modelo6);
		repositorio.save(vehiculo3);
		
		Vehiculo vehiculo4 = new Vehiculo("A323DDS");
		vehiculo4.setCliente(cliente4);
		vehiculo4.setMarca(marca2);
		vehiculo4.setModelo(modelo3);
		repositorio.save(vehiculo4);
		
		Vehiculo vehiculo5 = new Vehiculo("A123DFW");
		vehiculo5.setCliente(cliente8);
		vehiculo5.setMarca(marca6);
		vehiculo5.setModelo(modelo1);
		repositorio.save(vehiculo5);
		
		Vehiculo vehiculo6 = new Vehiculo("A123DYT");
		vehiculo6.setCliente(cliente5);
		vehiculo6.setMarca(marca7);
		vehiculo6.setModelo(modelo5);
		repositorio.save(vehiculo6);
*/
	}
}

