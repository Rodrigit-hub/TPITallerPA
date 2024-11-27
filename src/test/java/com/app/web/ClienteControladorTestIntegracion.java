package com.app.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.app.web.entidad.Cliente;
import com.app.web.repository.ClienteRepositorio;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClienteControladorTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(null, "Juan", "Pérez", "juan@example.com", "123456789", "Calle Falsa 123", "");
        clienteRepositorio.save(cliente);  // Guardar cliente real en la base de datos en memoria
    }


    // Prueba para mostrar el formulario de registro de cliente
    @Test
    void mostrarFormularioDeRegistroDeCliente() throws Exception {
    	
        mockMvc.perform(get("/clientes/nuevo"))
                .andExpect(status().isOk())
                .andExpect(view().name("crear_cliente"))
                .andExpect(model().attributeExists("cliente"));
    }

    // Prueba para guardar un nuevo cliente
    @Test
    void guardarCliente() throws Exception {
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nombre", "Onur")
                .param("apellido", "Gómez")
                .param("email", "carlos@example.com")
                .param("telefono", "987654321")
                .param("direccion", "Avenida Siempre Viva 456")
                .param("informacion", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clientes"));
        // Busca al cliente por su email, que es un campo único
        Cliente clienteGuardado = clienteRepositorio.findFirstByNombre("Onur");
        assertNotNull(clienteGuardado);  // Verifica que el cliente se haya guardado
        assertEquals("Onur", clienteGuardado.getNombre());  // Verifica que el nombre sea el esperado
        assertEquals("Gómez", clienteGuardado.getApellido());
    }
 // Prueba para listar todos los clientes

    @Test
    void listarClientes() throws Exception {

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(view().name("clientes"))
                .andExpect(model().attributeExists("clientes"));
    }
    @Test
    void actualizarCliente() throws Exception {
        // Actualizamos los datos del cliente existente
        mockMvc.perform(post("/clientes/" + cliente.getId() + "/editar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nombre", "Juan")
                .param("apellido", "Pérez")
                .param("email", "juan.nuevo@example.com")
                .param("telefono", "987654321")
                .param("direccion", "Calle Falsa 456")
                .param("informacion", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clientes"));

        // Verificamos que el cliente ha sido actualizado en la base de datos
        Cliente clienteActualizado = clienteRepositorio.findById(cliente.getId()).orElse(null);
        assertNotNull(clienteActualizado);
        assertEquals("juan.nuevo@example.com", clienteActualizado.getEmail());
    }

    // Prueba para eliminar un cliente existente
    @Test
    void eliminarCliente() throws Exception {
        mockMvc.perform(get("/clientes/" + cliente.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clientes"));

        Cliente clienteEliminado = clienteRepositorio.findById(cliente.getId()).orElse(null);
        assertNotNull(clienteEliminado);  // Verifica que el cliente aún existe en la base de datos
        assertTrue(clienteEliminado.isEliminado());  // Verifica que el cliente fue marcado como eliminado
    }
}
