package tech.edev404.votaciones.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tech.edev404.votaciones.model.dto.VotacionDTO;
import tech.edev404.votaciones.model.entity.Votacion;
import tech.edev404.votaciones.model.mapper.VotacionMapper;
import tech.edev404.votaciones.service.VotacionesService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/votaciones")
public class VotacionesController {

    private final VotacionesService votacionesService;
    private final VotacionMapper votacionMapper;

    @GetMapping
    public ResponseEntity<Page<VotacionDTO>> handleGetVotaciones(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Votacion> votaciones = votacionesService.getVotacionesPaginate(paging);
        if (votaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        Page<VotacionDTO> votacionesDTO = new PageImpl<VotacionDTO>(
                votaciones.map(votacionMapper::pojoToDto).toList());
        return ResponseEntity.status(HttpStatus.OK).body(votacionesDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<VotacionDTO>> handleSearchVotacion(
            @RequestBody VotacionDTO exampleDTO,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size) {

        Example<Votacion> example = Example.of(votacionMapper.dtoToPojo(exampleDTO));
        Pageable paging = PageRequest.of(page, size);
        Page<Votacion> votaciones = votacionesService.getVotacionesPaginateByExample(paging, example);
        if (votaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        Page<VotacionDTO> votacionesDTO = new PageImpl<VotacionDTO>(
                votaciones.map(votacionMapper::pojoToDto).toList());
        return ResponseEntity.status(HttpStatus.OK).body(votacionesDTO);
    }

    @PutMapping("current/{id}")
    public ResponseEntity<VotacionDTO> handleSetCurrentVotacion(@PathVariable Integer id)
            throws EntityNotFoundException {
        if (!votacionesService.alreadyExist(id)) {
            throw new EntityNotFoundException("VOTACION not found. Can not be setted current");
        }
        if (votacionesService.isDisabled(id)) {
            throw new IllegalStateException("VOTACION is not enabled. Can not be current");
        }
        votacionesService.setCurrentVotacion(id);
        VotacionDTO votacionDTO = votacionMapper.pojoToDto(votacionesService.getCurrentVotacion().get());
        return ResponseEntity.status(HttpStatus.OK).body(votacionDTO);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> handleUpdateVotacion(@RequestBody VotacionDTO votacionDTO)
            throws EntityNotFoundException {
        if (!votacionesService.alreadyExist(votacionDTO)) {
            throw new EntityNotFoundException("VOTACION not found. Can not be updated");
        }
        Votacion votacion = votacionMapper.dtoToPojo(votacionDTO);
        votacionesService.addVotacion(votacion);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> handleDeleteVotacion(@PathVariable("id") Integer idVotacion)
            throws EntityNotFoundException {
        if (!votacionesService.alreadyExist(idVotacion)) {
            throw new EntityNotFoundException("VOTACION not found. Can not be deleted");
        }
        votacionesService.deleteById(idVotacion);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> handleSaveVotacion(@RequestBody VotacionDTO votacionDTO)
            throws IllegalStateException {
        if (votacionesService.alreadyExist(votacionDTO)) {
            throw new IllegalStateException("VOTACION already exist. Can not be added");
        }
        Votacion votacion = votacionMapper.dtoToPojo(votacionDTO);
        votacionesService.addVotacion(votacion);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<HttpStatus> handleDisableVotacion(@PathVariable("id") Integer idVotacion)
            throws EntityNotFoundException, IllegalStateException {
        if (!votacionesService.alreadyExist(idVotacion)) {
            throw new EntityNotFoundException("VOTACION not found. Can not be disabled");
        } else if (votacionesService.isDisabled(idVotacion)) {
            throw new IllegalStateException("VOTACION already disabled. Do not request disable");
        }
        votacionesService.disableVotacionById(idVotacion);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/enable/{id}")
    public ResponseEntity<HttpStatus> handleEnableVotacion(@PathVariable("id") Integer idVotacion)
            throws EntityNotFoundException, IllegalStateException {
        if (!votacionesService.alreadyExist(idVotacion)) {
            throw new EntityNotFoundException("VOTACION not found. Can not be enabled");
        } else if (votacionesService.isEnabled(idVotacion)) {
            throw new IllegalStateException("VOTACION already enabled. Do not request enable");
        }
        votacionesService.enableVotacionById(idVotacion);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Feign client use case
    @GetMapping
    public ResponseEntity<Boolean> handleVotacionExistById(@RequestBody Integer idVotacion){
        return ResponseEntity.status(HttpStatus.OK).body(votacionesService.alreadyExist(idVotacion));
    }

    @InitBinder
    public void stringBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
