package com.pinapp.ms.clientes.pinapp.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String titulo;
    private String mensaje;
}
