package com.pinapp.ms.clientes.pinapp.cliente;

import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteCreacionRequestDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteResponseDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClientesKpiResponseDTO;
import com.pinapp.ms.clientes.pinapp.errors.NotFoundException;
import net.minidev.json.writer.FakeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteServiceImpl clienteService;

    List<Cliente> listClientes;

    @BeforeEach
    void setUp() {
        listClientes = List.of(
                new Cliente("Marta", "Gonzalez", LocalDate.of(1990, Month.FEBRUARY, 12), 34),
                new Cliente("Nilda", "Rodriguez", LocalDate.of(1950, Month.MARCH, 19), 74),
                new Cliente("Pablo", "Gutierrez", LocalDate.of(1974, Month.APRIL, 21), 50)
        );
    }

    @Test
    public void kpiClientes() {
        when(clienteRepository.findAll()).thenReturn(listClientes);
        ClientesKpiResponseDTO result = clienteService.obtenerKpiDeClientes();
        assertEquals(16.438437341250605d, result.getDesviacionEstandar());
        assertEquals(52.666666666666664d, result.getPromedioEdad());
    }

    @Test
    public void kpiClientes_throwNotFoundException() {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        NotFoundException e = assertThrows(NotFoundException.class, () ->
                clienteService.obtenerKpiDeClientes()
        );
        assertEquals("No se encontraron clientes", e.getMessage());
    }

    @Test
    public void listClientes() {
        Pageable pageable = Pageable.unpaged();

        when(clienteRepository.findAll(pageable)).thenReturn(new PageImpl<>(listClientes));
        when(clienteRepository.findAll()).thenReturn(listClientes);

        Page<ClienteResponseDTO> result = clienteService.listarClientes(pageable);
        assertTrue(result.hasContent());
    }

    @Test
    public void listClientes_throwNotFoundException() {
        Pageable pageable = Pageable.unpaged();

        when(clienteRepository.findAll(pageable)).thenReturn(Page.empty());
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());

        NotFoundException e = assertThrows(NotFoundException.class, () ->
                clienteService.listarClientes(pageable)
        );

        assertEquals("No se encontraron clientes", e.getMessage());
    }

    @Test
    public void creaClientes() {
        ClienteCreacionRequestDTO request = new ClienteCreacionRequestDTO("Marta", "Gonzalez", LocalDate.of(1980, Month.FEBRUARY, 12), 34);

        Cliente clienteCreado = new Cliente("Marta", "Gonzalez", LocalDate.of(1980, Month.FEBRUARY, 12), 34);
        when(clienteRepository.save(any())).thenReturn(clienteCreado);

        ClienteResponseDTO result = clienteService.crearCliente(request);
        assertEquals(clienteCreado.getEdad(), result.getEdad());
    }
}
