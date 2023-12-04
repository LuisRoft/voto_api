package ec.voto.api.v1;

import ec.voto.api.dto.ApiResponseDTO;
import ec.voto.api.dto.CandidatoDTO;
import ec.voto.api.service.CandidatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = { "/api/v1.0/candidato" })
public class CandidatoController {

	@Autowired
	private CandidatoService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<CandidatoDTO> list = service.findAll(new CandidatoDTO());
		ApiResponseDTO<List<CandidatoDTO>> response = new ApiResponseDTO<>(true, list);
		return (new ResponseEntity<Object>(response, HttpStatus.OK));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody CandidatoDTO CandidatoDTO) {
		CandidatoDTO CandidatoDTOResult = service.save(CandidatoDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, CandidatoDTOResult), HttpStatus.CREATED);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody CandidatoDTO CandidatoDTO) {
		CandidatoDTO resultDTO = service.update(CandidatoDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	}

	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		CandidatoDTO dto = new CandidatoDTO();
		dto.setId_candidato(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
	}

}
