package ec.voto.api.v1;

import ec.voto.api.dto.ApiResponseDTO;
import ec.voto.api.dto.VotoDTO;
import ec.voto.api.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = { "/api/v1.0/voto" })
public class VotoController {

	@Autowired
	private VotoService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<VotoDTO> list = service.findAll(new VotoDTO());
		ApiResponseDTO<List<VotoDTO>> response = new ApiResponseDTO<>(true, list);
		return (new ResponseEntity<Object>(response, HttpStatus.OK));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody VotoDTO VotoDTO) {
		VotoDTO VotoDTOResult = service.save(VotoDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, VotoDTOResult), HttpStatus.CREATED);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody VotoDTO VotoDTO) {
		VotoDTO resultDTO = service.update(VotoDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	}

	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		VotoDTO dto = new VotoDTO();
		dto.setId_voto(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
	}

}
