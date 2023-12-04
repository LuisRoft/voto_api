package ec.voto.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mesa")
public class Mesa {

	@Id
	@Column(updatable = false, nullable = false)
	private Long id_mesa;

	@Column(nullable = false, unique = true)
	private String ubicacion;
}
