package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class ProvinciaDTO {
	
	private Long id;
	
	private String nombre;

	private Long pais_id;

	private PaisDTO pais;
	
}
