package ec.voto.api.service;

import java.util.Optional;

import ec.voto.api.dto.PaisDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.voto.api.domain.Provincia;
import ec.voto.api.dto.ProvinciaDTO;
import ec.voto.api.repository.ProvinciaPersistence;

@Service
public class ProvinciaService extends GenericCrudServiceImpl<Provincia, ProvinciaDTO> {

	@Autowired
	private ProvinciaPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Provincia> find(ProvinciaDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public Provincia mapToDomain(ProvinciaDTO dto) {
		return modelMapper.map(dto, Provincia.class);
	}

	@Override
	public ProvinciaDTO mapToDto(Provincia domain) {
		ProvinciaDTO dto = modelMapper.map(domain, ProvinciaDTO.class);
		if (domain.getPais() != null) {
			PaisDTO paisDTO = modelMapper.map(domain.getPais(), PaisDTO.class);
			dto.setPais(paisDTO);
			dto.setPais_id(domain.getPais().getId());
		}
		return dto;
	}

	public ProvinciaDTO saveNot(Provincia provincia) {
		// Lógica para guardar la entidad
		Provincia savedProvincia = repository.save(provincia);
		return mapToDto(savedProvincia);
	}

}
