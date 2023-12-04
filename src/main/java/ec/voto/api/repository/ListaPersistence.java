package ec.voto.api.repository;

import ec.voto.api.domain.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaPersistence extends JpaRepository<Lista, Long> {

}
