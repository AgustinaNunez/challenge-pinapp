package com.pinapp.ms.clientes.pinapp.cliente.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public ClienteResponseDTO(String nombre,
                              String apellido,
                              LocalDate fechaNacimiento,
                              Integer edad,
                              UUID id,
                              LocalDate fechaProbableMuerte) {
        super(nombre, apellido, fechaNacimiento, edad);
        this.id = id;
        this.fechaProbableMuerte = fechaProbableMuerte;
    }
}
