package com.pinapp.ms.clientes.pinapp.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    Cliente cliente, cliente1, cliente2;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setApellido("Gonzalez");
        cliente.setNombre("Pablo");
        cliente.setFechaNacimiento(LocalDate.of(1990, Month.FEBRUARY, 15));

        cliente1 = new Cliente("Juan", "Gomez", LocalDate.of(1985, 3, 10), 35);
        cliente2 = new Cliente("Juan", "Gomez", LocalDate.of(1985, 3, 10), 35);
    }

    @Test
    public void getEdad() {
        int edad = cliente.getEdad();
        assertTrue(edad > 0);
    }

    @Test
    public void getEdad_return0() {
        cliente.setFechaNacimiento(null);
        int edad = cliente.getEdad();
        assertEquals(0, edad);
    }

    @Test
    public void testToString() {
        String result = cliente.toString();
        String expected = String.format("Cliente(id=%s, nombre=%s, apellido=%s, fechaNacimiento=%s, edad=%s)",
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getFechaNacimiento(),
                cliente.getEdad()
        );
        assertEquals(expected, result);
    }

    @Test
    public void testEquals() {
        cliente1.setId(UUID.randomUUID());
        cliente2.setId(cliente1.getId());

        assertEquals(cliente1, cliente2);

        cliente2.setId(UUID.randomUUID());
        assertNotEquals(cliente1, cliente2);
    }

    @Test
    public void testHashCode() {
        cliente1.setId(UUID.randomUUID());
        cliente2.setId(cliente1.getId());

        assertEquals(cliente1.hashCode(), cliente2.hashCode());

        cliente2.setId(UUID.randomUUID());
        assertNotEquals(cliente1.hashCode(), cliente2.hashCode());
    }

    @Test
    void testCanEqual() {
        assertTrue(cliente1.canEqual(cliente2));
        assertTrue(cliente2.canEqual(cliente1));

        assertFalse(cliente1.canEqual(new Object()));
    }
}
