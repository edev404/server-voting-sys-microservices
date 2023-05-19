package tech.fabricatic.votaciones.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tech.fabricatic.votaciones.service.CandidatosService;
import tech.fabricatic.votaciones.service.VotacionesService;
import tech.fabricatic.votaciones.service.VotosService;
import tech.fabricatic.votaciones.service.feign.IdentidadFeignClient;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/votos")
@SuppressWarnings("unused")
public class VotosController {

  private final VotosService votosService;
  private final CandidatosService candidatosService;
  private final VotacionesService votacionesService;
  private final IdentidadFeignClient identidadFeignClient;

  @PostMapping("{idAprendiz}/vote-by/{idCandidato}")
  public ResponseEntity<HttpStatus> handleRegisterVoto(
      @PathVariable("idCandidato") Integer idCandidato,
      @PathVariable("idUsuario") String idUsuario,
      @RequestParam("idVotacion") Integer idVotacion) throws EntityNotFoundException {

    votosService.registerVote(idCandidato, idUsuario, idVotacion);
    return ResponseEntity.status(HttpStatus.OK).build();

  }

  @GetMapping("{idUsuario}/has-already-vote/{idVotacion}")
  public ResponseEntity<Boolean> handleHasAlreadyVote(
      @PathVariable String idUsuario,
      @PathVariable Integer idVotacion) {
    return ResponseEntity.status(HttpStatus.OK).body(votosService.hasAlreadyVote(idUsuario, idVotacion));
  }

  @CircuitBreaker(name = "usuariosfeignReport")
  @GetMapping("feign-working")
  public String feignWorking() {
    return identidadFeignClient.feignWorking();
  }

  @GetMapping("hello-world")
  public String helloWorld() {
    return "hello from votaciones microservice";
  }

}
