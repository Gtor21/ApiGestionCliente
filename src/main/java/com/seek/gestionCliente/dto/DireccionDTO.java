package com.seek.gestionCliente.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionDTO {

    private String calle;
    private String ciudad;
    private String departamento;
    private String codigoPostal;
}
