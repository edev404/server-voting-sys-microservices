package tech.fabricatic.votaciones.votos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.fabricatic.votaciones.votos.model.entity.Voto;


@Repository
public interface VotosRepository extends JpaRepository<Voto, Integer> {

    List<Voto> findByVotacion(Integer idVotacion);

    @Query(value="SELECT * FROM votos v WHERE v.idAprendiz= :idAprendiz AND v.idVotacion= :idVotacion LIMIT 1"  , nativeQuery = true)
    Optional<Voto> findByAprendizAndVotacion(String idAprendiz, Integer idVotacion);

}
