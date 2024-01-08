package ec.voto.api.repository;

import ec.voto.api.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudiantePersistence extends JpaRepository<Estudiante, Long> {
    @Query("SELECT u FROM Estudiante u WHERE u.estado_voto = :estadoVoto")
    List<Estudiante> findEstudiantesByEstadoVoto(Boolean estadoVoto);
}
