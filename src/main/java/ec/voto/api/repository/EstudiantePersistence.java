package ec.voto.api.repository;

import ec.voto.api.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudiantePersistence extends JpaRepository<Estudiante, Long> {

}
