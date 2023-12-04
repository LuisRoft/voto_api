package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class CandidatoDTO {
	private Long id_candidato;

	private String nombre_candidato;

	private String apellido_candidato;

	private String posicion;

	private ListaDTO lista;
	
}
