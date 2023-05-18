package tech.fabricatic.votaciones.votaciones.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tech.fabricatic.votaciones.votaciones.model.dto.VotacionDTO;
import tech.fabricatic.votaciones.votaciones.model.entity.Votacion;
import tech.fabricatic.votaciones.votaciones.model.enumerated.EstadoVotacion;
import tech.fabricatic.votaciones.votaciones.repository.VotacionesRepository;
import tech.fabricatic.votaciones.votaciones.service.VotacionesService;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class VotacionesServiceImpl implements VotacionesService {

    private final VotacionesRepository votacionesRepository;

    @Override
    public List<Votacion> getVotaciones() {
        return votacionesRepository.findAll();
    }

    @Override
    public void addVotacion(Votacion votacion) {
        votacionesRepository.save(votacion);
    }

    @Override
    public void deleteById(Integer idVotacion) {
        votacionesRepository.deleteById(idVotacion);
    }

    @Override
    public void deleteVotacion(Votacion votacion) {
        votacionesRepository.delete(votacion);
    }

    @Override
    public Page<Votacion> getVotacionesPaginate(Pageable paging) {
        return votacionesRepository.findAll(paging);
    }

    @Override
    public Page<Votacion> getVotacionesPaginateByExample(Pageable paging, Example<Votacion> example) {
        return votacionesRepository.findAll(example, paging);
    }

    @Override
    public Optional<Votacion> getVotacionById(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion);
    }

    @Override
    public void disableVotacionById(Integer idVotacion) {
        if(isThisCurrentVotacion(idVotacion)){
            setNotCurrentVotacion(idVotacion);
        }
        votacionesRepository.disableVotacionById(idVotacion);
    }

    @Override
    public void enableVotacionById(Integer idVotacion) {
        votacionesRepository.enableVotacionById(idVotacion);
    }

    @Override
    public List<Votacion> getVotacionesByEstado(EstadoVotacion estado) {
        return votacionesRepository.findAll()
                .stream()
                .filter(votacion -> votacion.getEstado().equals(estado))
                .toList();
    }

    @Override
    public Boolean alreadyExist(VotacionDTO votacionDTO) {
        return votacionesRepository.findByNombreAndDescripcion(votacionDTO.getNombre(), votacionDTO.getDescripcion())
                .isPresent();
    }

    @Override
    public Boolean alreadyExist(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion).isPresent();
    }

    @Override
    public Boolean isDisabled(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion).get().getEstado().equals(EstadoVotacion.INHABILITADA);
    }

    @Override
    public Boolean isEnabled(Integer idVotacion) {
        return votacionesRepository.findById(idVotacion).get().getEstado().equals(EstadoVotacion.HABILITADA);
    }

    @Override
    public Optional<Votacion> getCurrentVotacion() {
        return votacionesRepository.findByCurrentVotacion(true);
    }

    @Override
    public void setCurrentVotacion(Integer idVotacion) {
        if (isAnyCurrentSelected()) {
            Optional<Votacion> currentOptional = votacionesRepository.findByCurrentVotacion(true);
            if (currentOptional.isPresent()) {
                votacionesRepository.setNotCurrentById(currentOptional.get().getId());
            }
        }
        votacionesRepository.setCurrentById(idVotacion);
    }

    @Override
    public Boolean isAnyCurrentSelected() {
        return votacionesRepository.findByCurrentVotacion(true).isPresent();
    }

    @Override
    public Boolean isThisCurrentVotacion(Integer idVotacion) {
        Optional<Votacion> optional = votacionesRepository.findByCurrentVotacion(true);
        if (optional.isPresent()) {
            return optional.get().getId() == idVotacion;
        }
        return false;
    }

    @Override
    public void setNotCurrentVotacion(Integer idVotacion) {
        if (isThisCurrentVotacion(idVotacion)) {
            votacionesRepository.setNotCurrentById(idVotacion);
        }
    }

    @Override
    public Optional<Integer> getCurrentVotacionId() {
        Optional<Votacion> optional = getCurrentVotacion();
        Integer idVotacion = optional.isPresent() ? optional.get().getId() : null;
        return Optional.ofNullable(idVotacion);
    }

}
