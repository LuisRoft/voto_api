package ec.voto.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id_curso;

	@Column(nullable = false, unique = true)
	private String nombre_curso;

	@Column(nullable = false, unique = true)
	private String paralelo;

	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	@JoinColumn(name = "id_mesa")
	private Mesa mesa;

}
