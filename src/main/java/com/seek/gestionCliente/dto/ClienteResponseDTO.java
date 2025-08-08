package com.seek.gestionCliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta con la información de un cliente")
public class ClienteResponseDTO {

    @Schema(description = "Identificador único del cliente", example = "1")
    private Long id;

    @Schema(description = "Nombre del cliente", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "perez")
    private String apellido;

    @Schema(description = "Correo electrónico del cliente", example = "pruebas@email.com")
    private String email;

    @Schema(description = "Fecha de nacimiento del cliente", example = "1990-05-20")
    private LocalDate fechaNacimiento;

    @Schema(description = "Género del cliente", example = "Masculino")
    private String genero;

    @Schema(description = "Número de teléfono del cliente", example = "3008602800")
    private String telefono;

    @Schema(description = "Fecha en la que el cliente fue registrado", example = "2025-08-08")
    private LocalDate fechaRegistro;

    @Schema(description = "Fecha estimada de fallecimiento del cliente", example = "2070-05-20")
    private LocalDate fechaEstimadaMuerte;

    @Schema(description = "Dirección del cliente")
    private DireccionDTO direccion;

}
