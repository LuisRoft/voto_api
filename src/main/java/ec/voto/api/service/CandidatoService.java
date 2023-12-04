package ec.voto.api.service;

import ec.voto.api.domain.Candidato;
import ec.voto.api.dto.CandidatoDTO;
import ec.voto.api.repository.CandidatoPersistence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatoService extends GenericCrudServiceImpl<Candidato, CandidatoDTO> {

	@Autowired
	private CandidatoPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Candidato> find(CandidatoDTO dto) {
		return repository.findById(dto.getId_candidato());
	}

	@Override
	public Candidato mapToDomain(CandidatoDTO dto) {
		return modelMapper.map(dto, Candidato.class);
	}

	@Override
	public CandidatoDTO mapToDto(Candidato domain) {
		return modelMapper.map(domain, CandidatoDTO.class);
	}

}
