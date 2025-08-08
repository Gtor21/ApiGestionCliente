package com.seek.gestionCliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Estadísticas sobre la edad de los clientes")
public class EstadisticasEdadDTO {


    @Schema(description = "Promedio de edad de los clientes", example = "35.4")
    private Double promedioEdad;


    @Schema(description = "Desviación estándar de la edad", example = "12.7")
    private Double desviacionEdad;

    public EstadisticasEdadDTO(Double promedioEdad, Double desviacionEdad) {
        this.promedioEdad = promedioEdad;
        this.desviacionEdad = desviacionEdad;
    }

}
