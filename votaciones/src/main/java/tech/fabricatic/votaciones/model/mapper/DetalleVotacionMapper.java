package tech.fabricatic.votaciones.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.fabricatic.votaciones.common.JsonMapper;
import tech.fabricatic.votaciones.model.entity.details.DetalleVotacion;

public class DetalleVotacionMapper implements JsonMapper<DetalleVotacion>{

    @Override
    public DetalleVotacion jsonToPojo(String jsonStr) throws JsonProcessingException {
        DetalleVotacion detalle = mapper.readValue(jsonStr, DetalleVotacion.class);
        return detalle;
    }
    
}
