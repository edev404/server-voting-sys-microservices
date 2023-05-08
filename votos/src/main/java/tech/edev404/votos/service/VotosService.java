package tech.edev404.votos.service;

import tech.edev404.votos.model.Aprendiz;
import tech.edev404.votos.model.Candidato;
import tech.edev404.votos.model.Votacion;

public interface VotosService {

    void registerVote(Candidato candidato, Aprendiz aprendiz, Votacion votacion);
    Boolean hasAlreadyVote(String idAprendiz, Integer idVotacion);
    void registerVote(Integer idCandidato, String idAprendiz, Integer idVotacion);
    
}
