package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@ToString
public class VotoDTO {
	private Long id_voto;

	private EstudianteDTO estudiante;

	private ListaDTO lista;

	private Timestamp fecha_voto;
}
