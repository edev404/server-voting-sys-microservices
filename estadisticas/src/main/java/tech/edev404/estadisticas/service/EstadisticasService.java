package tech.edev404.estadisticas.service;

import tech.edev404.estadisticas.model.Estadistica;
import tech.edev404.estadisticas.model.dto.VotacionDTO;

public interface EstadisticasService {

    Estadistica getEstadisticas(VotacionDTO votacion);
    
}
