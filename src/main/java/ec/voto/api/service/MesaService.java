package ec.voto.api.service;

import ec.voto.api.domain.Mesa;
import ec.voto.api.dto.MesaDTO;
import ec.voto.api.repository.MesaPersistence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MesaService extends GenericCrudServiceImpl<Mesa, MesaDTO> {

	@Autowired
	private MesaPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Mesa> find(MesaDTO dto) {
		return repository.findById(dto.getId_mesa());
	}

	@Override
	public Mesa mapToDomain(MesaDTO dto) {
		return modelMapper.map(dto, Mesa.class);
	}

	@Override
	public MesaDTO mapToDto(Mesa domain) {
		return modelMapper.map(domain, MesaDTO.class);
	}

}
