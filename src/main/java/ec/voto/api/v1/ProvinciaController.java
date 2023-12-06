package ec.voto.api.v1;

import java.util.List;

import ec.voto.api.service.PaisService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.voto.api.dto.ApiResponseDTO;
import ec.voto.api.dto.ProvinciaDTO;
import ec.voto.api.service.ProvinciaService;
import jakarta.validation.Valid;

import ec.voto.api.domain.Pais;
import ec.voto.api.domain.Provincia;
import java.util.Optional;

@RestController
@RequestMapping(value = { "/api/v1.0/provincia" })
public class ProvinciaController {

	@Autowired
	private PaisService paisService;

	@Autowired
	private ProvinciaService provinciaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<ProvinciaDTO> list = provinciaService.findAll(new ProvinciaDTO());
		ApiResponseDTO<List<ProvinciaDTO>> response = new ApiResponseDTO<>(true, list);
		return (new ResponseEntity<Object>(response, HttpStatus.OK));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody ProvinciaDTO ProvinciaDTO) {
//		ProvinciaDTO ProvinciaDTOResult = paisService.save(ProvinciaDTO);
//		return new ResponseEntity<>(new ApiResponseDTO<>(true, ProvinciaDTOResult), HttpStatus.CREATED);
		try {
			// Verifica que el país con el ID proporcionado exista
			Optional<Pais> paisOptional = paisService.findById(ProvinciaDTO.getPais_id());

			if (paisOptional.isPresent()) {
				// Asocia la provincia al país encontrado
				Provincia provincia = new Provincia();
				provincia.setNombre(ProvinciaDTO.getNombre());
				provincia.setPais(paisOptional.get());

				// Guarda la provincia en la base de datos
				ProvinciaDTO result = provinciaService.saveNot(provincia);

				return ResponseEntity.ok(result);
			} else {
				// Manejo de error si el país no existe
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("El país con ID " + ProvinciaDTO.getPais_id() + " no existe");
			}
		} catch (EntityNotFoundException e) {
			// Manejo de error si no se encuentra la entidad
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entidad no encontrada: " + e.getMessage());
		} catch (Exception e) {
			// Manejo de otros errores
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al crear la provincia: " + e.getMessage());
		}
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody ProvinciaDTO ProvinciaDTO) {
		ProvinciaDTO resultDTO = provinciaService.update(ProvinciaDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	}

	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		ProvinciaDTO dto = new ProvinciaDTO();
		dto.setId(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, provinciaService.find(dto)), HttpStatus.OK);
	}

}
