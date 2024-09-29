package com.pinapp.ms.clientes.pinapp.cliente;

import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteCreacionRequestDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteResponseDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClientesKpiResponseDTO;
import com.pinapp.ms.clientes.pinapp.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private static final double ESPERANZA_VIDA_MUNDIAL = 80;

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

    @Override
    public ClienteResponseDTO crearCliente(ClienteCreacionRequestDTO request) {
        Cliente clienteACrear = clienteMapper.toCliente(request);
        Cliente clienteCreado = clienteRepository.save(clienteACrear);
        return clienteMapper.toClienteResponseDTO(clienteCreado);
    }

    @Override
    public Page<ClienteResponseDTO> listarClientes(Pageable pageable) {
        Page<Cliente> clientePage = clienteRepository.findAll(pageable);
        ClientesKpiResponseDTO kpiClientes = obtenerKpiDeClientes();
        return clientePage.map(cliente -> {
            ClienteResponseDTO dto = clienteMapper.toClienteResponseDTO(cliente);
            dto.setFechaProbableMuerte(calcularFechaProbableMuerte(dto.getFechaNacimiento(), kpiClientes));
            return dto;
        });
    }

    @Override
    public ClientesKpiResponseDTO obtenerKpiDeClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new NotFoundException("No se encontraron clientes");
        }

        double promedioEdad = calcularPromedioEdad(clientes);
        double desviacionEstandar = calcularDesviacionEstandar(clientes);

        return new ClientesKpiResponseDTO(promedioEdad, desviacionEstandar);
    }

    private LocalDate calcularFechaProbableMuerte(LocalDate fechaNacimiento, ClientesKpiResponseDTO kpiClientes) {
        return fechaNacimiento.plusYears((long) ESPERANZA_VIDA_MUNDIAL);
    }

    private double calcularPromedioEdad(List<Cliente> clientes) {
        return clientes.stream()
                .mapToInt(Cliente::getEdad)
                .average()
                .orElse(0);
    }

    private double calcularDesviacionEstandar(List<Cliente> clientes) {
        double promedio = calcularPromedioEdad(clientes);
        double suma = clientes.stream()
                .mapToDouble(cliente -> Math.pow(cliente.getEdad() - promedio, 2))
                .sum();
        return Math.sqrt(suma / clientes.size());
    }
}
