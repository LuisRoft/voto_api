package ec.voto.api.repository;

import ec.voto.api.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoPersistence extends JpaRepository<Voto, Long> {

}
