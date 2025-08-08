package com.seek.gestionCliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionDTO {

    @Schema(description = "Nombre de la calle", example = "Calle 12")
    private String calle;

    @Schema(description = "ciudad de residencia", example = "San antonio")
    private String ciudad;

    @Schema(description = "Departamento de residencia", example = "Santander")
    private String departamento;

    @Schema(description = "Numero de codigo postal de la ciudad", example = "700001")
    private String codigoPostal;
}
