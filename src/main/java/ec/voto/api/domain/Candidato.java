package ec.voto.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "candidato")
public class Candidato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id_candidato;

	@Column(nullable = false, unique = true)
	private String nombre_candidato;

	@Column(nullable = false, unique = true)
	private String apellido_candidato;

	@Column(nullable = false, unique = true)
	private String posicion;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "id_lista")
	private Lista lista;
}
