package com.seek.gestionCliente.repository;

import com.seek.gestionCliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query(value = """
        SELECT 
            AVG(TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE())) AS promedioEdad,
            STDDEV(TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE())) AS desviacionEdad
        FROM clientes c
        """, nativeQuery = true)
    Object[] obtenerEstadisticasEdad();
}
