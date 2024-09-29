package com.pinapp.ms.clientes.pinapp.cliente;

import com.pinapp.ms.clientes.pinapp.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
