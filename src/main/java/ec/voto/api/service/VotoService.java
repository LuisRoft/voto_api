package ec.voto.api.service;

import ec.voto.api.domain.Voto;
import ec.voto.api.dto.VotoDTO;
import ec.voto.api.repository.VotoPersistence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoService extends GenericCrudServiceImpl<Voto, VotoDTO> {

	@Autowired
	private VotoPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Voto> find(VotoDTO dto) {
		return repository.findById(dto.getId_voto());
	}

	@Override
	public Voto mapToDomain(VotoDTO dto) {
		return modelMapper.map(dto, Voto.class);
	}

	@Override
	public VotoDTO mapToDto(Voto domain) {
		return modelMapper.map(domain, VotoDTO.class);
	}

}
