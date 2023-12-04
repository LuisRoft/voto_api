package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public class ListaDTO {
	private Long id_lista;

	private String nombre_lista;

	private Date fecha_creacion;
}
