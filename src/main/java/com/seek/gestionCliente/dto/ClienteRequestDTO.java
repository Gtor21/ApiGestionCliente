package com.seek.gestionCliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDTO {

    @Schema(description = "Nombre del cliente", example = "Juan")
    @NotBlank
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Perez")
    @NotBlank
    private String apellido;

    @Schema(description = "correo electronico del cliente", example = "prueba@gmail.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "Fecha de nacimiento", example = "1990-05-20")
    @Past
    private LocalDate fechaNacimiento;

    @Schema(description = "Genero del cliente", example = "Masculino")
    @NotBlank
    private String genero;

    @Schema(description = "Numero de telefono", example = "3008605800")
    private String telefono;

    @Schema(description = "Dirección del cliente")
    private DireccionDTO direccion;
}
