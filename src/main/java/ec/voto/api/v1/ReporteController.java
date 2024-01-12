package ec.voto.api.v1;

import ec.voto.api.dto.*;
import ec.voto.api.service.CandidatoService;
import ec.voto.api.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = { "/api/v1.0/reporte" })
public class ReporteController {

	@Autowired
	private ReporteService service;

	@GetMapping("/votosTotales")
	public ResponseEntity<Object> generarVotosTotales() {
		List<ListaVotosDTO> resultados = service.generarVotosTotales();
		ApiResponseDTO<List<ListaVotosDTO>> response = new ApiResponseDTO<>(true, resultados);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/votosPorMesa")
	public ResponseEntity<Object> generarVotosPorMesa() {
		List<ListaVotosMesaDTO> resultados = service.generarVotosPorMesa();
		ApiResponseDTO<List<ListaVotosMesaDTO>> response = new ApiResponseDTO<>(true, resultados);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/padronElectoral", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> generarPadronElectoral() {
		List<PadronElectoralDTO> resultados = service.generarPadronElectoral();
		ApiResponseDTO<List<PadronElectoralDTO>> response = new ApiResponseDTO<>(true, resultados);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
