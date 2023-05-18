package tech.fabricatic.votaciones.votaciones.model.mapper;

import org.springframework.stereotype.Service;

import tech.fabricatic.votaciones.common.GenericMapper;
import tech.fabricatic.votaciones.votaciones.model.dto.VotacionDTO;
import tech.fabricatic.votaciones.votaciones.model.entity.Votacion;


@Service
public class VotacionMapper implements GenericMapper<Votacion, VotacionDTO>{

    @Override
    public Votacion dtoToPojo(VotacionDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dtoToPojo'");
    }

    @Override
    public VotacionDTO pojoToDto(Votacion pojo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pojoToDto'");
    }
    
    
}
