package com.seek.gestionCliente.service.impl;

import com.seek.gestionCliente.dto.ClienteRequestDTO;
import com.seek.gestionCliente.dto.ClienteResponseDTO;
import com.seek.gestionCliente.dto.DireccionDTO;
import com.seek.gestionCliente.dto.EstadisticasEdadDTO;
import com.seek.gestionCliente.entity.ActividadCliente;
import com.seek.gestionCliente.entity.Cliente;
import com.seek.gestionCliente.entity.Direccion;
import com.seek.gestionCliente.exception.ResourceNotFoundException;
import com.seek.gestionCliente.mapper.ClienteMapper;
import com.seek.gestionCliente.mapper.DireccionMapper;
import com.seek.gestionCliente.repository.ClienteRepository;
import com.seek.gestionCliente.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.webresources.DirResourceSet;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO crearCliente(ClienteRequestDTO dto) {
        Direccion direccion = Direccion.builder()
                .calle(dto.getDireccion().getCalle())
                .ciudad(dto.getDireccion().getCiudad())
                .departamento(dto.getDireccion().getDepartamento())
                .codigoPostal(dto.getDireccion().getCodigoPostal())
                .build();

        Cliente cliente = Cliente.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .genero(dto.getGenero())
                .email(dto.getEmail())
                .fechaNacimiento(dto.getFechaNacimiento())
                .telefono(dto.getTelefono())
                .fechaRegistro(LocalDate.now())
                .direccion(direccion)
                .build();

        ActividadCliente actividad = ActividadCliente.builder()
                .cliente(cliente)
                .tipoActividad("default")
                .fecha(LocalDateTime.now())
                .descripcion("Cliente creado")
                .build();

        cliente.setActividadCliente(List.of(actividad));

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return ClienteMapper.mapToResponseDTO(clienteGuardado);
    }

    @Override
    public Optional<ClienteResponseDTO> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ClienteResponseDTO.builder()
                        .id(cliente.getId())
                        .nombre(cliente.getNombre())
                        .apellido(cliente.getApellido())
                        .email(cliente.getEmail())
                        .fechaNacimiento(cliente.getFechaNacimiento())
                        .genero(cliente.getGenero())
                        .telefono(cliente.getTelefono())
                        .fechaRegistro(cliente.getFechaRegistro())
                        .fechaEstimadaMuerte(
                                cliente.getFechaNacimiento() != null
                                        ? cliente.getFechaNacimiento().plusYears(80)
                                        : null
                        )
                        .direccion(DireccionMapper.mapearDireccion(cliente.getDireccion()))
                        .build());
    }

    @Override
    public Optional<Cliente> obtenerClientePorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }


    @Override
    public List<ClienteResponseDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(cliente -> ClienteResponseDTO.builder()
                        .id(cliente.getId())
                        .nombre(cliente.getNombre())
                        .apellido(cliente.getApellido())
                        .email(cliente.getEmail())
                        .fechaNacimiento(cliente.getFechaNacimiento())
                        .genero(cliente.getGenero())
                        .telefono(cliente.getTelefono())
                        .fechaRegistro(cliente.getFechaRegistro())
                        .fechaEstimadaMuerte(
                                cliente.getFechaNacimiento() != null
                                        ? cliente.getFechaNacimiento().plusYears(80)
                                        : null
                        )
                        .direccion(DireccionMapper.mapearDireccion(cliente.getDireccion()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));

        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setApellido(clienteActualizado.getApellido());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setGenero(clienteActualizado.getGenero());
        clienteExistente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());

        if (clienteExistente.getDireccion() != null && clienteActualizado.getDireccion() != null) {
            clienteExistente.getDireccion().setCalle(clienteActualizado.getDireccion().getCalle());
            clienteExistente.getDireccion().setCiudad(clienteActualizado.getDireccion().getCiudad());
            clienteExistente.getDireccion().setDepartamento(clienteActualizado.getDireccion().getDepartamento());
            clienteExistente.getDireccion().setCodigoPostal(clienteActualizado.getDireccion().getCodigoPostal());
        }
        return clienteRepository.save(clienteExistente);
    }


    @Override
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public EstadisticasEdadDTO obtenerEstadisticasEdad() {
        Object[] fila = (Object[]) clienteRepository.obtenerEstadisticasEdad()[0];

        Double promedio = fila[0] != null ? ((Number) fila[0]).doubleValue() : 0.0;
        Double desviacion = fila[1] != null ? ((Number) fila[1]).doubleValue() : 0.0;

        return new EstadisticasEdadDTO(promedio, desviacion);
    }

}
