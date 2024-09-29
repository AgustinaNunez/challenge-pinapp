package com.pinapp.ms.clientes.pinapp.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientesKpiResponseDTO {
    private double promedioEdad;
    private double desviacionEstandar;
}
