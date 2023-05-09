package tech.edev404.votos.service.implementation;

import org.springframework.stereotype.Service;

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


    
}
