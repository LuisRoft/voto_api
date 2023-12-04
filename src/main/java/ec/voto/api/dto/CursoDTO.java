package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class CursoDTO {
	private Long id_curso;

	private String nombre_curso;

	private String paralelo;

	private MesaDTO mesa;
}
