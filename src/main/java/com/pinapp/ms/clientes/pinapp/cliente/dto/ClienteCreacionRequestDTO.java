package com.pinapp.ms.clientes.pinapp.cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreacionRequestDTO {
    @NotNull(message = "Nombre es requerido")
    @NotEmpty(message = "Nombre no puede ser vacío")
    @Schema(description = "Nombre del cliente", example = "Juan")
    private String nombre;

    @NotNull(message = "Apellido es requerido")
    @NotEmpty(message = "Apellido no puede ser vacío")
    @Schema(description = "Apellido del cliente", example = "González")
    private String apellido;

    @NotNull(message = "Fecha de nacimiento es requerido")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "Fecha de nacimiento del cliente", example = "1997-01-10")
    private LocalDate fechaNacimiento;

    @Positive(message = "Edad no puede ser un valor negativo")
    @Schema(description = "Edad del cliente", example = "27")
    private Integer edad;
}
