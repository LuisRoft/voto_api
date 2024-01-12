package ec.voto.api.service;

import ec.voto.api.domain.Curso;
import ec.voto.api.domain.Estudiante;
import ec.voto.api.domain.Lista;
import ec.voto.api.domain.Voto;
import ec.voto.api.dto.*;
import ec.voto.api.repository.CursoPersistence;
import ec.voto.api.repository.EstudiantePersistence;
import ec.voto.api.repository.ListaPersistence;
import ec.voto.api.repository.VotoPersistence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteService {

	@Autowired
	private EstudiantePersistence estudianteRepository;

	@Autowired
	private VotoPersistence votoRepository;

	@Autowired
	private ListaPersistence listaRepository;

	@Autowired
	private CursoPersistence cursoRepository;

	public List<ListaVotosDTO> generarVotosTotales() {
		List<Voto> votos = votoRepository.findAll();

		Map<Long, Long> votosPorLista = votos.stream()
				.collect(Collectors.groupingBy(voto -> voto.getLista().getId_lista(), Collectors.counting()));

		// Obtener la información de las listas
		List<Lista> listas = listaRepository.findAll();

		// Crear la lista de resultados
		List<ListaVotosDTO> resultados = new ArrayList<>();

		// Iterar sobre las listas y construir los resultados
		for (Lista lista : listas) {
			ListaVotosDTO resultado = new ListaVotosDTO();
			resultado.setNombre_lista(lista.getNombre_lista());

			// Obtener el número de votos para la lista actual
			Long votosParaLista = votosPorLista.getOrDefault(lista.getId_lista(), 0L);
			resultado.setVotosTotales(votosParaLista);

			resultados.add(resultado);
		}

		return resultados;
	}

	public List<ListaVotosMesaDTO> generarVotosPorMesa() {
		// Obtener la lista de listas
		List<Lista> listas = listaRepository.findAll();

		// Obtener la lista de votos
		List<Voto> votos = votoRepository.findAll();

		// Mapear los votos a estudiantes y luego obtener el id de la mesa
		Map<Long, Map<Long, Long>> votosPorMesaYLista = votos.stream()
				.collect(Collectors.groupingBy(this::obtenerIdMesaDesdeVoto,
						Collectors.groupingBy(voto -> voto.getLista().getId_lista(), Collectors.counting())));

		// Crear la lista de resultados
		List<ListaVotosMesaDTO> resultados = new ArrayList<>();

		// Iterar sobre las mesas y construir los resultados
		for (Map.Entry<Long, Map<Long, Long>> entry : votosPorMesaYLista.entrySet()) {
			Long idMesa = entry.getKey();
			Map<Long, Long> votosPorLista = entry.getValue();

			// Iterar sobre las listas y construir los resultados por mesa
			for (Lista lista : listas) {
				ListaVotosMesaDTO resultado = new ListaVotosMesaDTO();
				resultado.setId_mesa(idMesa);
				resultado.setNombre_lista(lista.getNombre_lista());

				// Obtener el número de votos para la lista actual en la mesa actual
				Long votosParaListaEnMesa = votosPorLista.getOrDefault(lista.getId_lista(), 0L);
				resultado.setVotosTotales(votosParaListaEnMesa);

				resultados.add(resultado);
			}
		}

		return resultados;
	}

	private Long obtenerIdMesaDesdeVoto(Voto voto) {
		Optional<Estudiante> estudiante = estudianteRepository.findById(voto.getEstudiante().getId_estudiante());
		return estudiante.map(est -> est.getCurso().getMesa().getId_mesa()).orElse(null);
	}

	public List<PadronElectoralDTO> generarPadronElectoral() {
		// Obtener la lista de estudiantes
		List<Estudiante> estudiantes = estudianteRepository.findAll();

		// Crear la lista de resultados
		List<PadronElectoralDTO> resultados = new ArrayList<>();

		// Iterar sobre los estudiantes y construir los resultados
		for (Estudiante estudiante : estudiantes) {
			CursoDTO cursoDTO = getCursoDTO(estudiante);
			PadronElectoralDTO resultado = new PadronElectoralDTO();
			resultado.setCurso(cursoDTO);
			resultado.setNombre_estudiante(estudiante.getNombre());
			resultado.setApellido_estudiante(estudiante.getApellido());

			// Agregar el resultado a la lista
			resultados.add(resultado);
		}

		return resultados;
	}

	private static CursoDTO getCursoDTO(Estudiante estudiante) {
		CursoDTO cursoDTO = new CursoDTO();
		MesaDTO mesaDTO = new MesaDTO();
		cursoDTO.setId_curso(estudiante.getCurso().getId_curso());
		cursoDTO.setNombre_curso(estudiante.getCurso().getNombre_curso());
		cursoDTO.setParalelo(estudiante.getCurso().getParalelo());
		mesaDTO.setId_mesa(estudiante.getCurso().getMesa().getId_mesa());
		mesaDTO.setNombre(estudiante.getCurso().getMesa().getNombre());
		mesaDTO.setUbicacion(estudiante.getCurso().getMesa().getUbicacion());
		cursoDTO.setMesa(mesaDTO);
		return cursoDTO;
	}


}
