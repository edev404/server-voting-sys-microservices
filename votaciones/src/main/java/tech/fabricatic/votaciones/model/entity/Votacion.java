package tech.fabricatic.votaciones.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.fabricatic.votaciones.model.enumerated.EstadoVotacion;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="votaciones", indexes = @Index(name="votaciones_unique", columnList = "nombre, idCentro", unique = true))
public class Votacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 250, nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoVotacion estado;

    @Column(nullable = false, name = "idCentro")
    private String centro;

    @Lob
    @Column(nullable = false)
    private String detalleVotacion;

    @Column(nullable = false)
    private Boolean currentVotacion;
    
    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime lastModified;
    
}