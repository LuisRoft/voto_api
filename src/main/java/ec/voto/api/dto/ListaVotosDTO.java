package ec.voto.api.dto;

import ec.voto.api.domain.Lista;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class ListaVotosDTO {
	private String nombre_lista;

	private long votosTotales;
	
}
