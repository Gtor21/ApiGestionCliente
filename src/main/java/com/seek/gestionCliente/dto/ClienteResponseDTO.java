package com.seek.gestionCliente.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaNacimiento;
    private String genero;
    private String telefono;
    private LocalDate fechaRegistro;
    private LocalDate fechaEstimadaMuerte;
    private DireccionDTO direccion;

}
