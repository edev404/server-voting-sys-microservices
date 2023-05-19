package tech.fabricatic.identidad.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="centro", indexes = @Index(name="centro_unique", columnList = "", unique = true))
public class Centro {

    @Id
    private Long id;

    @OneToMany(mappedBy = "idCentro", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Usuario> usuarios;
    
}
