package tech.fabricatic.estadisticas.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.fabricatic.estadisticas.model.dto.CandidatoDTO;
import tech.fabricatic.estadisticas.model.dto.VotacionDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estadistica {
    
    private VotacionDTO votacion;

    private Long cantidadVotos;

    private Long votantesHabilitados;

    private List<CandidatoDTO> candidatos;

    private Map<String, Long> votosPorCandidato;

    private CandidatoDTO candidatoMasVotado;
    
    private String creationDateTime;

}
