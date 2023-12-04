package ec.voto.api.repository;

import ec.voto.api.domain.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaPersistence extends JpaRepository<Mesa, Long> {

}
