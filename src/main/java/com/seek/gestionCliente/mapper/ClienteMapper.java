package com.seek.gestionCliente.mapper;

import com.seek.gestionCliente.dto.ClienteRequestDTO;
import com.seek.gestionCliente.dto.ClienteResponseDTO;
import com.seek.gestionCliente.dto.DireccionDTO;
import com.seek.gestionCliente.entity.Cliente;
import com.seek.gestionCliente.entity.Direccion;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public static Cliente mapToEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .fechaNacimiento(dto.getFechaNacimiento())
                .genero(dto.getGenero())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion() != null
                        ? Direccion.builder()
                        .calle(dto.getDireccion().getCalle())
                        .ciudad(dto.getDireccion().getCiudad())
                        .departamento(dto.getDireccion().getDepartamento())
                        .codigoPostal(dto.getDireccion().getCodigoPostal())
                        .build()
                        : null)
                .build();
    }

    public static ClienteResponseDTO mapToResponseDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getEmail())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .genero(cliente.getGenero())
                .telefono(cliente.getTelefono())
                .fechaRegistro(cliente.getFechaRegistro())
                .direccion(cliente.getDireccion() != null
                        ? DireccionDTO.builder()
                        .calle(cliente.getDireccion().getCalle())
                        .ciudad(cliente.getDireccion().getCiudad())
                        .departamento(cliente.getDireccion().getDepartamento())
                        .codigoPostal(cliente.getDireccion().getCodigoPostal())
                        .build()
                        : null)
                .build();
    }
}
