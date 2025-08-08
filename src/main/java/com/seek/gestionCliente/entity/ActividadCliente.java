package com.seek.gestionCliente.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "actividades_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActividadCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoActividad; // Ej: "COMPRA", "LOGIN", "SOLICITUD"

    private String descripcion;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}
