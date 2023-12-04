package ec.voto.api.v1;

import ec.voto.api.dto.ApiResponseDTO;
import ec.voto.api.dto.ListaDTO;
import ec.voto.api.service.ListaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = { "/api/v1.0/lista" })
public class ListaController {

	@Autowired
	private ListaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<ListaDTO> list = service.findAll(new ListaDTO());
		ApiResponseDTO<List<ListaDTO>> response = new ApiResponseDTO<>(true, list);
		return (new ResponseEntity<Object>(response, HttpStatus.OK));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody ListaDTO ListaDTO) {
		ListaDTO ListaDTOResult = service.save(ListaDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, ListaDTOResult), HttpStatus.CREATED);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody ListaDTO ListaDTO) {
		ListaDTO resultDTO = service.update(ListaDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	}

	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		ListaDTO dto = new ListaDTO();
		dto.setId_lista(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
	}

}
