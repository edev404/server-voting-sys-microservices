package tech.edev404.votos.service.implementation;

import org.springframework.stereotype.Service;

import tech.edev404.votos.model.Aprendiz;
import tech.edev404.votos.model.Candidato;
import tech.edev404.votos.model.Votacion;
import tech.edev404.votos.service.VotosService;

@Service
public class VotosServiceImpl implements VotosService {

    @Override
    public Boolean hasAlreadyVote(String idAprendiz, Integer idVotacion) {
        return true;
    }

    @Override
    public void registerVote(Integer idCandidato, String idAprendiz, Integer idVotacion) {
        
    }

    @Override
    public void registerVote(Candidato candidato, Aprendiz aprendiz, Votacion votacion) {
        
    }

    
}
