package ec.voto.api.service;

import ec.voto.api.domain.Curso;
import ec.voto.api.domain.Mesa;
import ec.voto.api.dto.CursoDTO;
import ec.voto.api.repository.CursoPersistence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService extends GenericCrudServiceImpl<Curso, CursoDTO> {

	@Autowired
	private CursoPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Curso> find(CursoDTO dto) {
		return repository.findById(dto.getId_curso());
	}

	@Override
	public Curso mapToDomain(CursoDTO dto) {
		return modelMapper.map(dto, Curso.class);
	}

	@Override
	public CursoDTO mapToDto(Curso domain) {
		return modelMapper.map(domain, CursoDTO.class);
	}

}
