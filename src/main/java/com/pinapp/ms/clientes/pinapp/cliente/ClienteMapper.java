package com.pinapp.ms.clientes.pinapp.cliente;

import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteCreacionRequestDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {
    Cliente toCliente(ClienteCreacionRequestDTO cliente);
    ClienteResponseDTO toClienteResponseDTO(Cliente cliente);
}
