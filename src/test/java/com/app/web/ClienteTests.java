package com.app.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.web.entidad.Cliente;
import com.app.web.repository.ClienteRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class ClienteTests {
	
	@Autowired
	private ClienteRepositorio repositorio;
	
	
	@Test
	@Rollback(false)
	public void testGuardarCliente() {
		Cliente cliente = new Cliente("Rodrigo","Alves", "rodri@gmail.com","4525566", "Santa fe 120", "DNI");
		Cliente clienteGuardado = repositorio.save(cliente);
		
		assertNotNull(clienteGuardado); // confirma la prueba unitaria siempre que clienteGuardado no sea nulo
	}
	@Test
	public void testBuscarClientePorNombre() {
		String nombre = "Ana";
		Cliente cliente = repositorio.findFirstByNombre(nombre);
		
		assertThat(cliente.getNombre()).isEqualTo(nombre);
	}
	
    @Test
    public void testBuscarClientesPorNombre() {
        String nombre = "Celia";
        List<Cliente> clientes = repositorio.findAllByNombre(nombre);

        assertThat(clientes).isNotEmpty(); // Asegúrate de que la lista no está vacía
        for (Cliente cliente : clientes) {
            assertThat(cliente.getNombre()).isEqualTo(nombre);
        }
    }
}
