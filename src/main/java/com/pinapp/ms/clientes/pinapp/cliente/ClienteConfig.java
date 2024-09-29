package com.pinapp.ms.clientes.pinapp.cliente;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ClienteConfig {
    @Bean
    CommandLineRunner userProfileCommandLineRunner(ClienteRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    new Cliente("Juan", "Gomez", LocalDate.of(1985, Month.MARCH, 10), 35),
                    new Cliente("Ana", "Martinez", LocalDate.of(1990, Month.JULY, 25), 28),
                    new Cliente("Carlos", "Lopez", LocalDate.of(1978, Month.DECEMBER, 5), 42),
                    new Cliente("Lucia", "Diaz", LocalDate.of(1995, Month.JANUARY, 17), 26),
                    new Cliente("Miguel", "Fernandez", LocalDate.of(1983, Month.APRIL, 30), 38),
                    new Cliente("Sofia", "Rodriguez", LocalDate.of(1992, Month.OCTOBER, 12), 29),
                    new Cliente("Javier", "Garcia", LocalDate.of(1975, Month.AUGUST, 20), 46),
                    new Cliente("Laura", "Perez", LocalDate.of(1988, Month.MAY, 3), 33),
                    new Cliente("Daniel", "Sanchez", LocalDate.of(1982, Month.NOVEMBER, 22), 39),
                    new Cliente("Maria", "Gonzalez", LocalDate.of(1993, Month.SEPTEMBER, 14), 28),
                    new Cliente("Luis", "Hernandez", LocalDate.of(1979, Month.JUNE, 11), 42),
                    new Cliente("Patricia", "Ramos", LocalDate.of(1987, Month.APRIL, 18), 34),
                    new Cliente("Fernando", "Romero", LocalDate.of(1991, Month.FEBRUARY, 7), 30),
                    new Cliente("Clara", "Gutierrez", LocalDate.of(1984, Month.JUNE, 29), 37),
                    new Cliente("Pedro", "Alvarez", LocalDate.of(1996, Month.SEPTEMBER, 21), 25),
                    new Cliente("Gabriela", "Ruiz", LocalDate.of(1994, Month.DECEMBER, 15), 27)
            ));
        };
    }
}
