package com.seek.gestionCliente.service;

import com.seek.gestionCliente.dto.ClienteRequestDTO;
import com.seek.gestionCliente.dto.ClienteResponseDTO;
import com.seek.gestionCliente.dto.EstadisticasEdadDTO;
import com.seek.gestionCliente.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    ClienteResponseDTO crearCliente(ClienteRequestDTO dto);

    Optional<ClienteResponseDTO> obtenerClientePorId(Long id);

    Optional<Cliente> obtenerClientePorEmail(String email);

    List<ClienteResponseDTO> listarClientes();

    Cliente actualizarCliente(Long id, Cliente clienteActualizado);

    void eliminarCliente(Long id);

    EstadisticasEdadDTO obtenerEstadisticasEdad();
}
