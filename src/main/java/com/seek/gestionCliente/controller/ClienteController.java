package com.seek.gestionCliente.controller;

import com.seek.gestionCliente.dto.ClienteRequestDTO;
import com.seek.gestionCliente.dto.ClienteResponseDTO;
import com.seek.gestionCliente.dto.DireccionDTO;
import com.seek.gestionCliente.dto.EstadisticasEdadDTO;
import com.seek.gestionCliente.entity.Cliente;
import com.seek.gestionCliente.exception.ResourceNotFoundException;
import com.seek.gestionCliente.mapper.ClienteMapper;
import com.seek.gestionCliente.service.IClienteService;
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
public class ClienteController {

    private final IClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO response = clienteService.crearCliente(dto);
        return ResponseEntity.created(URI.create("/api/clientes/" + response.getId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerCliente(@PathVariable Long id) {
        ClienteResponseDTO cliente = clienteService.obtenerClientePorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> lista = clienteService.listarClientes();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        Cliente actualizado = clienteService.actualizarCliente(id, ClienteMapper.mapToEntity(dto));
        return ResponseEntity.ok(ClienteMapper.mapToResponseDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasEdadDTO> obtenerEstadisticas() {
        return ResponseEntity.ok(clienteService.obtenerEstadisticasEdad());
    }

}
