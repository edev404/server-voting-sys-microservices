package tech.fabricatic.votaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tech.fabricatic.votaciones.model.dto.VotacionDTO;
import tech.fabricatic.votaciones.model.entity.Votacion;
import tech.fabricatic.votaciones.model.enumerated.EstadoVotacion;
import tech.fabricatic.votaciones.repository.VotacionesRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class VotacionesService {

    private final VotacionesRepository votacionesRepository;

    public List<Votacion> getVotaciones() {
        return votacionesRepository.findAll();
    }

    public void addVotacion(Votacion votacion) {
        votacionesRepository.save(votacion);
    }

    public void deleteById(Integer idVotacion) {
        votacionesRepository.deleteById(idVotacion);
    }

    public void deleteVotacion(Votacion votacion) {
        votacionesRepository.delete(votacion);
    }

    public Page<Votacion> getVotacionesPaginate(Pageable paging, Example<Votacion> example) {
        return votacionesRepository.findAll(example, paging);
    }

    public Page<Votacion> getVotacionesPaginateByExample(Pageable paging, Example<Votacion> example) {
        return votacionesRepository.findAll(example, paging);
    }

    public Optional<Votacion> getVotacionById(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion);
    }

    public void disableVotacionById(Integer idVotacion, String idCentro) {
        if(isThisCurrentVotacion(idVotacion, idCentro)){
            setNotCurrentVotacion(idVotacion, idCentro);
        }
        votacionesRepository.disableVotacionById(idVotacion);
    }

    public void enableVotacionById(Integer idVotacion) {
        votacionesRepository.enableVotacionById(idVotacion);
    }

    public List<Votacion> getVotacionesByEstado(EstadoVotacion estado, String idCentro) {
        Example<Votacion> example = Example.of(Votacion.builder().estado(estado).centro(idCentro).build());
        return votacionesRepository.findAll(example);
    }

    public Boolean alreadyExist(VotacionDTO votacionDTO) {
        Example<Votacion> example = Example.of(Votacion.builder().nombre(votacionDTO.getNombre()).centro(votacionDTO.getCentro()).build());
        return votacionesRepository.exists(example);
    }

    public Boolean alreadyExist(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion).isPresent();
    }

    public Boolean isDisabled(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion).get().getEstado().equals(EstadoVotacion.INHABILITADA);
    }

    public Boolean isEnabled(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion).get().getEstado().equals(EstadoVotacion.HABILITADA);
    }

    public Optional<Votacion> getCurrentVotacion(String idCentro) {
        Example<Votacion> example = Example.of(Votacion.builder().currentVotacion(true).centro(idCentro).build());
        return votacionesRepository.findOne(example);
    }

    public void setCurrentVotacion(Integer idVotacion, String idCentro) {
        Optional<Votacion> current = getCurrentVotacion(idCentro);
        if (current.isPresent()) {
            if (!current.get().getId().equals(idVotacion)) {
                votacionesRepository.setNotCurrentById(current.get().getId());
            } else {
                return;
            }
        }
        votacionesRepository.setCurrentById(idVotacion);
    }

    public Boolean isAnyCurrentSelected(String idCentro) {
        Example<Votacion> example = Example.of(Votacion.builder().currentVotacion(true).centro(idCentro).build());
        return votacionesRepository.exists(example);
    }

    public Boolean isThisCurrentVotacion(Integer idVotacion, String idCentro) {
        Example<Votacion> example = Example.of(Votacion.builder().id(idVotacion).currentVotacion(true).build());
        return votacionesRepository.exists(example);
    }

    public void setNotCurrentVotacion(Integer idVotacion, String idCentro) {
        if (isThisCurrentVotacion(idVotacion, idCentro)) {
            votacionesRepository.setNotCurrentById(idVotacion);
        }
    }

    public Optional<Integer> getCurrentVotacionId(String idCentro) {
        Optional<Votacion> optional = getCurrentVotacion(idCentro);
        Integer idVotacion = optional.isPresent() ? optional.get().getId() : null;
        return Optional.ofNullable(idVotacion);
    }

}
