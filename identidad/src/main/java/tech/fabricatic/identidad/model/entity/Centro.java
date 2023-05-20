package tech.fabricatic.identidad.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="centro")
public class Centro {

    @Id
    private String id;

    @OneToMany(mappedBy = "centro", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Usuario> usuarios;
    
}
