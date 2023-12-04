package ec.voto.api.repository;

import ec.voto.api.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoPersistence extends JpaRepository<Curso, Long> {

}
