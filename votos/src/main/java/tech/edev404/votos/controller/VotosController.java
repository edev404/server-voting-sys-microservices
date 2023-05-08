package tech.edev404.votos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.edev404.votos.service.VotosService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/votos")
public class VotosController {

    private final VotosService votosService;
    
    @PostMapping("{idAprendiz}/vote-by/{idCandidato}")
    public ResponseEntity<HttpStatus> handleRegisterVoto(
                @PathVariable("idCandidato") Integer idCandidato, 
                @PathVariable("idAprendiz") String idAprendiz,
                @RequestParam("idVotacion") Integer idVotacion) {

        //Logica para registrar voto
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
