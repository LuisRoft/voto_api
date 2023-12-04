package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class EstudianteDTO {
	private Long id_estudiante;

	private String nombre;

	private String apellido;

	private Integer edad;

	private CursoDTO curso;
}
