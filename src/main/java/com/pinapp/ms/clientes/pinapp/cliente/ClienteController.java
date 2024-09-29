package com.pinapp.ms.clientes.pinapp.cliente;

import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteCreacionRequestDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClienteResponseDTO;
import com.pinapp.ms.clientes.pinapp.cliente.dto.ClientesKpiResponseDTO;
import com.pinapp.ms.clientes.pinapp.errors.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/clientes")
@Slf4j
@Tag(name = "Clientes", description = "Operaciones de clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping(value = "/creacliente",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Crea un nuevo cliente",
            description = "Crea un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos ingresados inválidos")
    })
    public ResponseEntity<ClienteResponseDTO> creaCliente(
            @Valid @RequestBody ClienteCreacionRequestDTO request
    ) {
        log.info("[creaCliente] request: {}", request);
        ClienteResponseDTO response = clienteService.crearCliente(request);
        log.info("[creaCliente] response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Obtiene KPI de los clientes",
            description = "Obtiene el promedio de edad y su desviación estandar de todos los clientes guardados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "KPI procesado exitosamente")
    })
    public ResponseEntity<ClientesKpiResponseDTO> kpiClientes() {
        log.info("[kpiClientes] inicia proceso");
        ClientesKpiResponseDTO response = clienteService.obtenerKpiDeClientes();
        log.info("[kpiClientes] response: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/listclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Lista paginada de los clientes guardados",
            description = "Lista paginada de los clientes guardados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado completo de clientes obtenido exitosamente"),
            @ApiResponse(responseCode = "206", description = "Listado parcial de clientes obtenido exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos ingresados inválidos",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponseDTO.class))})
    })
    public ResponseEntity<List<ClienteResponseDTO>> listClientes(
            @Parameter(description = "Número de página", example = "1")
            @RequestParam(defaultValue = "1") int numeroPagina,

            @Parameter(description = "Cantidad de elementos por página", example = "10")
            @RequestParam(defaultValue = "10") int elementosPagina
    ) {
        log.info("[listClientes] numeroPagina: {}, elementosPagina: {}", numeroPagina, elementosPagina);
        Pageable pageable = PageRequest.of(numeroPagina-1, elementosPagina, Sort.unsorted());
        Page<ClienteResponseDTO> page = clienteService.listarClientes(pageable);

        HttpStatus httpStatus = page.hasNext() ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK;
        log.info("[listClientes] página {}/{} con {}/{} clientes, httpStatus: {}",
                page.getNumber(),
                page.getTotalPages(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                httpStatus);
        return ResponseEntity.status(httpStatus).body(page.getContent());
    }
}
