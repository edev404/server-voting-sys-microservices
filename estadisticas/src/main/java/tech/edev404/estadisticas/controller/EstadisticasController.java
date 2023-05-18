package tech.edev404.estadisticas.controller;

import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import tech.edev404.estadisticas.model.Estadistica;
import tech.edev404.estadisticas.model.dto.VotacionDTO;
import tech.edev404.estadisticas.service.EstadisticasService;
import tech.edev404.estadisticas.service.feign.VotacionFeignClient;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/estadisticas")
public class EstadisticasController {

    private final VotacionFeignClient votacionesFeignClient;
    private final EstadisticasService estadisticasService;

    @GetMapping
    public ResponseEntity<Estadistica> handleGetEstadisticas(@PathVariable("id") VotacionDTO votacion)
            throws IllegalStateException {        
        Predicate<Integer> votacionesAlreadyExist = v -> {
            ResponseEntity<Boolean> response = votacionesFeignClient.handleVotacionExistById(v);
            return response.hasBody() && response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))
                    ? response.getBody() : false;
        };
        if (!votacionesAlreadyExist.test(votacion.getId())) {
            throw new IllegalStateException ("VOTACION not found. ESTADISTICAS can not be provided");
        } else if (votacion.getEstado().equalsIgnoreCase("HABILITADA")) {
            throw new IllegalStateException("VOTACION is not disabled. ESTADISTICAS can not be provided");
        }
        Estadistica estadisticas = estadisticasService.getEstadisticas(votacion);
        return ResponseEntity.status(HttpStatus.OK).body(estadisticas);
    }
}
