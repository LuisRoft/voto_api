package ec.voto.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class Estudiante {

	@Id
	@Column(updatable = false, nullable = false)
	private Long id_estudiante;

	@Column(nullable = false, unique = true)
	private String nombre;

	@Column(nullable = false, unique = true)
	private String apellido;

	@Column(nullable = false, unique = true)
	private Integer edad;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "id_curso")
	private Curso curso;

}
