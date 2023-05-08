package tech.edev404.votos.service;

import tech.edev404.votos.model.Aprendiz;
import tech.edev404.votos.model.Candidato;
import tech.edev404.votos.model.Votacion;

public interface VotosService {

    void registerVote(Candidato candidato, Aprendiz aprendiz, Votacion votacion);
    boolean hasAlreadyVote(Aprendiz aprendiz, Votacion votacion);
    
}
