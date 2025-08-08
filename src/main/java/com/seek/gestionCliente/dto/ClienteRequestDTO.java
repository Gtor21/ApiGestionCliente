package com.seek.gestionCliente.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Email
    @NotBlank
    private String email;

    @Past
    private LocalDate fechaNacimiento;

    @NotBlank
    private String genero;

    private String telefono;

    private DireccionDTO direccion;
}
