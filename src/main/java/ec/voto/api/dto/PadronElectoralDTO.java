package ec.voto.api.dto;

import ec.voto.api.domain.Curso;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class PadronElectoralDTO {

	private CursoDTO curso;

	private String nombre_estudiante;
	private String apellido_estudiante;
}
