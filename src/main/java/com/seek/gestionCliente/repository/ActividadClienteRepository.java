package com.seek.gestionCliente.repository;

import com.seek.gestionCliente.entity.ActividadCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadClienteRepository extends JpaRepository<ActividadCliente, Long> {

    List<ActividadCliente> findByClienteId(Long clienteId);
    List<ActividadCliente> findByTipoActividad(String tipoActividad);
}
