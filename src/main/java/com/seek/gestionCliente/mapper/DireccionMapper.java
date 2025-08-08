package com.seek.gestionCliente.mapper;

import com.seek.gestionCliente.dto.DireccionDTO;
import com.seek.gestionCliente.entity.Direccion;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

    public static DireccionDTO mapearDireccion(Direccion direccion) {
        if (direccion == null) return null;

        return DireccionDTO.builder()
                .calle(direccion.getCalle())
                .ciudad(direccion.getCiudad())
                .departamento(direccion.getDepartamento())
                .codigoPostal(direccion.getCodigoPostal())
                .build();
    }

}
