package tech.fabricatic.estadisticas.service;

import tech.fabricatic.estadisticas.model.Estadistica;
import tech.fabricatic.estadisticas.model.dto.VotacionDTO;

public interface EstadisticasService {

    Estadistica getEstadisticas(VotacionDTO votacion);
    
}
