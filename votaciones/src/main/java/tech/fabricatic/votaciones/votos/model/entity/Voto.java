package tech.fabricatic.votaciones.votos.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.fabricatic.votaciones.votaciones.model.entity.Votacion;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="votos", indexes = @Index(name="votos_unique", columnList = "idVotacion, idAprendiz", unique = true))
public class Voto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,  name = "idCandidato")
    private Integer candidato;

    @Column(nullable = false, name = "idUsuario")
    private Integer usuario;

    @Column(nullable = false,  name = "idVotacion")
    private Votacion votacion;

    @CreationTimestamp
    private LocalDateTime fechaRegistro;
    
    private Boolean valido;

}
