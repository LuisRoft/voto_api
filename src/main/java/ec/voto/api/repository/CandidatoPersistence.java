package ec.voto.api.repository;

import ec.voto.api.domain.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoPersistence extends JpaRepository<Candidato, Long> {

}
