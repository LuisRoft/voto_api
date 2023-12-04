package ec.voto.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "lista")
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id_lista;

	@Column(nullable = false, unique = true)
	private String nombre_lista;

	@Column(nullable = false, unique = true)
	private Date fecha_creacion;
}
