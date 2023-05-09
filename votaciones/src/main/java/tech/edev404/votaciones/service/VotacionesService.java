package tech.edev404.votaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tech.edev404.votaciones.model.dto.VotacionDTO;
import tech.edev404.votaciones.model.entity.Votacion;
import tech.edev404.votaciones.model.enumerated.EstadoVotacion;

public interface VotacionesService {

    List<Votacion> getVotaciones();

    void addVotacion(Votacion votacion);

    void deleteById(Integer idVotacion);

    void deleteVotacion(Votacion votacion);

    Page<Votacion> getVotacionesPaginate(Pageable paging);

    Page<Votacion> getVotacionesPaginateByExample(Pageable paging, Example<Votacion> example);

    Optional<Votacion> getVotacionById(Integer idVotacion);

    void disableVotacionById(Integer idVotacion);

    void enableVotacionById(Integer idVotacion);

    List<Votacion> getVotacionesByEstado(EstadoVotacion estado);

    Boolean alreadyExist(VotacionDTO votacionDTO);

    Boolean alreadyExist(Integer idVotacion);

    Boolean isEnabled(Integer idVotacion);

    Boolean isDisabled(Integer idVotacion);

    Optional<Votacion> getCurrentVotacion();

    Optional<Integer> getCurrentVotacionId();

    void setCurrentVotacion(Integer idVotacion);

    void setNotCurrentVotacion(Integer idVotacion);

    Boolean isAnyCurrentSelected();

    Boolean isThisCurrentVotacion(Integer idVotacion);
    
}
