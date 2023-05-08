package tech.edev404.votos.controller;

import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tech.edev404.votos.service.VotosService;
import tech.edev404.votos.service.feign.AprendicesFeignClient;
import tech.edev404.votos.service.feign.CandidatosFeignClient;
import tech.edev404.votos.service.feign.VotacionesFeignClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/votos")
public class VotosController {

    private final VotosService votosService;
    private final AprendicesFeignClient aprendicesFeignClient;
    private final CandidatosFeignClient candidatosFeignClient;
    private final VotacionesFeignClient votacionesFeignClient;

    @PostMapping("{idAprendiz}/vote-by/{idCandidato}")
    public ResponseEntity<HttpStatus> handleRegisterVoto(
            @PathVariable("idCandidato") Integer idCandidato,
            @PathVariable("idAprendiz") String idAprendiz,
            @RequestParam("idVotacion") Integer idVotacion) throws EntityNotFoundException {

        Predicate<Integer> candidatoAlreadyExist = c -> {
            ResponseEntity<Boolean> response = candidatosFeignClient.handleCandidatoExistById(c);
            return response.hasBody() && response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))
                    ? response.getBody()
                    : false;
        };
        Predicate<String> aprendizAlreadyExist = a -> {
            ResponseEntity<Boolean> response = aprendicesFeignClient.handleAprendizExist(a);
            return response.hasBody() && response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))
                    ? response.getBody()
                    : false;
        };
        Predicate<Integer> votacionesAlreadyExist = v -> {
            ResponseEntity<Boolean> response = votacionesFeignClient.handleVotacionExistById(v);
            return response.hasBody() && response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))
                    ? response.getBody()
                    : false;
        };

        if (!aprendizAlreadyExist.test(idAprendiz) && !candidatoAlreadyExist.test(idCandidato)
                && !votacionesAlreadyExist.test(idVotacion)) {
            throw new EntityNotFoundException("message");
        }

        votosService.registerVote(idCandidato, idAprendiz, idVotacion);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("{idAprendiz}/has-already-vote/{idVotacion}")
    public ResponseEntity<Boolean> handleHasAlreadyVote(
            @PathVariable String idAprendiz,
            @PathVariable Integer idVotacion) {
        return ResponseEntity.status(HttpStatus.OK).body(votosService.hasAlreadyVote(idAprendiz, idVotacion));
    }

}
