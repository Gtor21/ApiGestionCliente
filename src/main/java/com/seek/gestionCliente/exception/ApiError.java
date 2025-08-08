package com.seek.gestionCliente.exception;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
    private String path;
}
