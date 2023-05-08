package tech.edev404.votos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.edev404.votos.model.entity.Voto;

@Repository
public interface VotosRepository extends JpaRepository<Voto, Integer>{
    
}
