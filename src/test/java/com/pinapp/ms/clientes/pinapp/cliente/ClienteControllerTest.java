package com.pinapp.ms.clientes.pinapp.cliente;

import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteCreacionRequestDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteResponseDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClientesKpiResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {
    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    public void kpiClientes() {
        ClientesKpiResponseDTO dto = new ClientesKpiResponseDTO(75.15d, 35.55d);
        when(clienteService.obtenerKpiDeClientes()).thenReturn(dto);

        ResponseEntity<ClientesKpiResponseDTO> result = clienteController.kpiClientes();
        assertEquals(dto, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void listClientes_ok() {
        List<ClienteResponseDTO> listClientes = List.of(
                new ClienteResponseDTO(),
                new ClienteResponseDTO(),
                new ClienteResponseDTO()
        );
        Page<ClienteResponseDTO> pageClientes = new PageImpl<>(listClientes, PageRequest.of(0, 10), listClientes.size());
        when(clienteService.listarClientes(any(Pageable.class))).thenReturn(pageClientes);

        ResponseEntity<List<ClienteResponseDTO>> result = clienteController.listClientes(1, 10);
        assertEquals(listClientes.size(), result.getBody().size());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void listClientes_partialContent() {
        List<ClienteResponseDTO> listClientes = List.of(
                new ClienteResponseDTO(),
                new ClienteResponseDTO(),
                new ClienteResponseDTO()
        );
        Page<ClienteResponseDTO> pageClientes = new PageImpl<>(listClientes, PageRequest.of(0, 4), 6);
        when(clienteService.listarClientes(any(Pageable.class))).thenReturn(pageClientes);

        ResponseEntity<List<ClienteResponseDTO>> result = clienteController.listClientes(1, 4);
        assertNotNull(result.getBody());
        assertEquals(listClientes.size(), result.getBody().size());
        assertEquals(HttpStatus.PARTIAL_CONTENT, result.getStatusCode());
    }

    @Test
    public void crearClientes() {
        ClienteCreacionRequestDTO request = new ClienteCreacionRequestDTO();

        ClienteResponseDTO response = new ClienteResponseDTO();
        when(clienteService.crearCliente(request)).thenReturn(response);

        ResponseEntity<ClienteResponseDTO> result = clienteController.creaCliente(request);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }
}
