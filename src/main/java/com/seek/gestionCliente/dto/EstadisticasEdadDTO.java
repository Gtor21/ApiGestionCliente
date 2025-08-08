package com.seek.gestionCliente.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class EstadisticasEdadDTO {
    private Double promedioEdad;
    private Double desviacionEdad;

    public EstadisticasEdadDTO(Double promedioEdad, Double desviacionEdad) {
        this.promedioEdad = promedioEdad;
        this.desviacionEdad = desviacionEdad;
    }

}
