package ec.voto.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.voto.api.domain.Pais;
import ec.voto.api.dto.PaisDTO;
import ec.voto.api.repository.PaisPersistence;

@Service
public class PaisService extends GenericCrudServiceImpl<Pais, PaisDTO> {

	@Autowired
	private PaisPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Pais> find(PaisDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public Pais mapToDomain(PaisDTO dto) {
		return modelMapper.map(dto, Pais.class);
	}

	@Override
	public PaisDTO mapToDto(Pais domain) {
		return modelMapper.map(domain, PaisDTO.class);
	}

	public Optional<Pais> buscarPorCodigo(String codigo) {
		Optional<Pais> entidad = repository.findByCodigo(codigo);
		return entidad;
	}

	public Optional<Pais> findById(Long id) {
		return repository.findById(id);
	}

}
