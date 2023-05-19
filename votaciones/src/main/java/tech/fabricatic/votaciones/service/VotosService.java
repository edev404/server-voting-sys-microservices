package tech.fabricatic.votaciones.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;



@Service
@Primary
public class VotosService {

    public Boolean hasAlreadyVote(String idAprendiz, Integer idVotacion) {
        return true;
    }

    public void registerVote(Integer idCandidato, String idAprendiz, Integer idVotacion) {
        
    }
    
}
