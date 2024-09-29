package com.pinapp.ms.clientes.pinapp.cliente.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClienteResponseDTO extends ClienteCreacionRequestDTO {
    private UUID id;
    private LocalDate fechaProbableMuerte;
}
