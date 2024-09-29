package com.pinapp.ms.clientes.pinapp.cliente;

import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteCreacionRequestDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteResponseDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClientesKpiResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    ClienteResponseDTO crearCliente(ClienteCreacionRequestDTO request);
    Page<ClienteResponseDTO> listarClientes(Pageable pageable);
    ClientesKpiResponseDTO obtenerKpiDeClientes();
}
