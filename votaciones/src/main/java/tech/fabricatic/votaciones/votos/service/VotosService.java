package tech.fabricatic.votaciones.votos.service;

public interface VotosService {

    Boolean hasAlreadyVote(String idAprendiz, Integer idVotacion);
    void registerVote(Integer idCandidato, String idAprendiz, Integer idVotacion);
    
}
