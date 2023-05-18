package tech.fabricatic.estadisticas.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id","documento", "imagen", "idVotacion", "propuestas"})
public class CandidatoDTO {
    private Integer id;
    @NotEmpty
    private String documento;
    private String imagen;
    @NotEmpty
    @JsonAlias("votacion")
    private Integer idVotacion;
    @NotEmpty
    private String propuestas;
}
