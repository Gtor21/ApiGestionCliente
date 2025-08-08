package com.seek.gestionCliente.controller;

import com.seek.gestionCliente.dto.ClienteRequestDTO;
import com.seek.gestionCliente.dto.ClienteResponseDTO;
import com.seek.gestionCliente.dto.DireccionDTO;
import com.seek.gestionCliente.dto.EstadisticasEdadDTO;
import com.seek.gestionCliente.entity.Cliente;
import com.seek.gestionCliente.exception.ResourceNotFoundException;
import com.seek.gestionCliente.mapper.ClienteMapper;
import com.seek.gestionCliente.service.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operaciones CRUD y estadísticas sobre clientes")
public class ClienteController {

    private final IClienteService clienteService;

    @Operation(summary = "Crear un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO response = clienteService.crearCliente(dto);
        return ResponseEntity.created(URI.create("/api/clientes/" + response.getId())).body(response);
    }

    @Operation(summary = "Obtener un cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerCliente(@PathVariable Long id) {
        ClienteResponseDTO cliente = clienteService.obtenerClientePorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Listar todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> lista = clienteService.listarClientes();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Actualizar un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        Cliente actualizado = clienteService.actualizarCliente(id, ClienteMapper.mapToEntity(dto));
        return ResponseEntity.ok(ClienteMapper.mapToResponseDTO(actualizado));
    }

    @Operation(summary = "Eliminar un cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener estadísticas de edad de los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadísticas calculadas correctamente",
                    content = @Content(schema = @Schema(implementation = EstadisticasEdadDTO.class)))
    })
    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasEdadDTO> obtenerEstadisticas() {
        return ResponseEntity.ok(clienteService.obtenerEstadisticasEdad());
    }

}
