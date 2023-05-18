package tech.fabricatic.votaciones.votos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tech.fabricatic.votaciones.candidatos.service.CandidatosService;
import tech.fabricatic.votaciones.votaciones.service.VotacionesService;
import tech.fabricatic.votaciones.votos.service.VotosService;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/votos")
public class VotosController {

    private final VotosService votosService;
    private final CandidatosService candidatosService;
    private final VotacionesService votacionesService;

    @PostMapping("{idAprendiz}/vote-by/{idCandidato}")
    public ResponseEntity<HttpStatus> handleRegisterVoto(
            @PathVariable("idCandidato") Integer idCandidato,
            @PathVariable("idUsuario") String idUsuario,
            @RequestParam("idVotacion") Integer idVotacion) throws EntityNotFoundException {

        // if (!aprendizAlreadyExist.test(idAprendiz) && !candidatoAlreadyExist.test(idCandidato)
        //         && !votacionesAlreadyExist.test(idVotacion)) {
        //     throw new EntityNotFoundException("message");
        // }

        votosService.registerVote(idCandidato, idUsuario, idVotacion);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("{idUsuario}/has-already-vote/{idVotacion}")
    public ResponseEntity<Boolean> handleHasAlreadyVote(
            @PathVariable String idUsuario,
            @PathVariable Integer idVotacion) {
        return ResponseEntity.status(HttpStatus.OK).body(votosService.hasAlreadyVote(idUsuario, idVotacion));
    }

}
