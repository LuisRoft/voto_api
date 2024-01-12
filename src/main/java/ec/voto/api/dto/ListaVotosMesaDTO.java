package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class ListaVotosMesaDTO {

	private long id_mesa;

	private String nombre_lista;

	private long votosTotales;
	
}
