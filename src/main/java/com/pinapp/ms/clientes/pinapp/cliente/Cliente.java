package com.pinapp.ms.clientes.pinapp.cliente;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;

    @Transient
    @Getter(AccessLevel.NONE)
    private Integer edad;

    public Integer getEdad() {
        if (this.fechaNacimiento == null) {
            return 0;
        }
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    public Cliente(String nombre, String apellido, LocalDate fechaNacimiento, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
    }
}
