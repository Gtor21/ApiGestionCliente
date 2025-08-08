package com.seek.gestionCliente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String apellido;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @Past
    @Column(name= "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank
    @Column(nullable = false)
    private String genero;

    @Column
    private String telefono;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActividadCliente> actividadCliente;


}
