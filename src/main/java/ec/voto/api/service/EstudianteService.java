package ec.voto.api.service;

import ec.voto.api.domain.Curso;
import ec.voto.api.domain.Estudiante;
import ec.voto.api.dto.EstudianteDTO;
import ec.voto.api.repository.EstudiantePersistence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService extends GenericCrudServiceImpl<Estudiante, EstudianteDTO> {

	@Autowired
	private EstudiantePersistence repository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private ModelMapper modelMapper = new ModelMapper();

	public void eliminarEstudiantePorId(Long idEstudiante) {
		// Finalmente, eliminar el estudiante
		jdbcTemplate.update("DELETE FROM estudiante WHERE id_estudiante = ?", idEstudiante);
	}
	@Override
	public Optional<Estudiante> find(EstudianteDTO dto) {
		return repository.findById(dto.getId_estudiante());
	}

	@Override
	public Estudiante mapToDomain(EstudianteDTO dto) {
		return modelMapper.map(dto, Estudiante.class);
	}

	@Override
	public EstudianteDTO mapToDto(Estudiante domain) {
		return modelMapper.map(domain, EstudianteDTO.class);
	}

	public List<EstudianteDTO> findEstudiantesByEstadoVoto(Boolean estadoVoto) {
		List<Estudiante> usuariosList = repository.findEstudiantesByEstadoVoto(estadoVoto);
		return usuariosList.stream().map(this::mapToDto).collect(Collectors.toList());
	}


}
